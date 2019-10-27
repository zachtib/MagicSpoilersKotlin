package com.zachtib.spoilers.service

import com.zachtib.spoilers.announce.AnnounceBroadcaster
import com.zachtib.spoilers.database.SpoilersDatabase
import com.zachtib.spoilers.logging.Logger
import com.zachtib.spoilers.models.MagicSet
import com.zachtib.spoilers.scryfall.ScryfallService
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpoilerService @Inject constructor(
    private val scryfall: ScryfallService,
    private val database: SpoilersDatabase,
    private val broadcaster: AnnounceBroadcaster,
    private val logger: Logger
) {
    suspend fun updateTrackedSets() {
        logger.debug("Updating tracked sets")
        // 1. Request list of sets from api
        val apiSets = scryfall.getAllSets()
        // 2. Request list of sets from database
        val databaseSets = database.getAllSets()
        // 2.1. Determine which of those should have watched turned OFF
        val today = LocalDateTime.now().toLocalDate()
        val setsToUnwatch = databaseSets.filter { it.watched && it.releaseDate != null && it.releaseDate < today }
        // 2.2. Update those records
        for (set in setsToUnwatch) {
            database.insertOrUpdate(set)
        }
        // 3. Determine the list of sets from api that are not in the database
        val knownSetCodes = databaseSets.map { it.code }
        val newSetsFromApi = apiSets.filter { it.code !in knownSetCodes }
        // 4. For each of those, determine if it should begin watched
        val newSetsAndWatched = newSetsFromApi.map { set: MagicSet ->
            if (set.releaseDate != null && set.releaseDate > today) {
                set.copy(watched = true)
            } else {
                set
            }
        }
        // 5. Insert / update
        for (set in newSetsAndWatched) {
            database.insertOrUpdate(set)
        }
    }

    suspend fun refreshAllWatchedSets() {
        logger.debug("Refreshing watched sets")
        // 1. Get the list of all watched sets
        val watchedSets = database.getWatchedSets()
        logger.debug("watchedSets: $watchedSets")
        // 2. For each of those sets, call refreshSpoilersForSet()
        for (set in watchedSets) {
            refreshSpoilersForSet(set)
        }
    }

    suspend fun refreshSpoilersForSetCode(code: String) {
        logger.debug("Attempting to refresh $code...")
        database.getSet(code)?.let { set ->
            refreshSpoilersForSet(set)
        }
    }

    private suspend fun refreshSpoilersForSet(magicSet: MagicSet) {
        logger.debug("Refreshing ${magicSet.name}")
        // The things that need to happen:
        // 1. We need to make a request for all of the cards in the set from the api
        val allCardsFromApi = scryfall.getAllCardsInSetBySetCode(magicSet.code)
        // 2. We need to make a request for all of the previously known cards from the database
        val knownCards = database.getCardsInSet(magicSet)
        // 4. We need to remove the cards from the apiSet that exist in the database set.
        val knownCardIds = knownCards.map { it.scryfallId }
        val newCardsFromApi = allCardsFromApi.filter { it.scryfallId !in knownCardIds }
        // 5. We need to broadcast this new set of cards
        broadcaster.announceCards(newCardsFromApi)
        // 7. We need to store those models in the database.
        database.insertCardsInSet(magicSet, newCardsFromApi)
    }
}