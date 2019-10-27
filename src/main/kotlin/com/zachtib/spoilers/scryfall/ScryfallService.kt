package com.zachtib.spoilers.scryfall

import com.zachtib.spoilers.logging.Logger
import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScryfallService @Inject constructor(
    private val api: ScryfallApi,
    private val logger: Logger
) {

    suspend fun getAllSets(): List<MagicSet> {
        val response = api.sets()
        val data = response.data
        return data.map { it.toMagicSet() }
    }

    suspend fun getAllCardsInSet(set: MagicSet): List<MagicCard> {
        return getAllCardsInSetBySetCode(set.code)
    }

    suspend fun getAllCardsInSetBySetCode(code: String): List<MagicCard> {
        val response = api.cardsSearch("e:$code")
        val data = response.data
        return data.map { it.toMagicCard() }
    }
}