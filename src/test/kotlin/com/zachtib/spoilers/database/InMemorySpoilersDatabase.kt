package com.zachtib.spoilers.database

import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import javax.inject.Inject

class InMemorySpoilersDatabase @Inject constructor() : SpoilersDatabase {

    private val savedSets = mutableMapOf<String, MagicSet>()
    private val savedCards = mutableMapOf<MagicSet, MutableList<MagicCard>>()

    override suspend fun getAllSets(): List<MagicSet> {
        return savedSets.values.toList()
    }

    override suspend fun getWatchedSets(): List<MagicSet> {
        return savedSets.values.filter { it.watched }
    }

    override suspend fun getSet(code: String): MagicSet? {
        return savedSets[code] ?: emptySet
    }

    override suspend fun getCardsInSet(set: MagicSet): List<MagicCard> {
        return savedCards[set]?.toList() ?: listOf()
    }

    override suspend fun insertOrUpdate(set: MagicSet) {
        savedSets[set.code] = set
    }

    override suspend fun insertCardsInSet(set: MagicSet, cards: List<MagicCard>) {
        savedCards[set]?.let { listToUpdate: MutableList<MagicCard> ->
            listToUpdate += cards
        }
    }

    companion object {
        val emptySet = MagicSet("", "", "", null, false)
    }
}