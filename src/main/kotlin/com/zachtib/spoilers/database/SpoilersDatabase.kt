package com.zachtib.spoilers.database

import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet

interface SpoilersDatabase {
    suspend fun getAllSets(): List<MagicSet>
    suspend fun getWatchedSets(): List<MagicSet>
    suspend fun getSet(code: String): MagicSet?
    suspend fun getCardsInSet(set: MagicSet): List<MagicCard>

    suspend fun insertOrUpdate(set: MagicSet)
    suspend fun insertCardsInSet(set: MagicSet, cards: List<MagicCard>)
}