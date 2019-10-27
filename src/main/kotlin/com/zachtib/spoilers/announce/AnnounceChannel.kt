package com.zachtib.spoilers.announce

import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet

interface AnnounceChannel {
    suspend fun announceExpansions(magicSets: List<MagicSet>)
    suspend fun announceCards(cards: List<MagicCard>)
}