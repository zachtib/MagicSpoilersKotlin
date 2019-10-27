package com.zachtib.spoilers.announce

import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestAnnounceChannel @Inject constructor() : AnnounceChannel {

    val magicSets = mutableListOf<MagicSet>()
    val magicCards = mutableListOf<MagicCard>()

    override suspend fun announceExpansions(magicSets: List<MagicSet>) {
        this.magicSets.addAll(magicSets)
    }

    override suspend fun announceCards(cards: List<MagicCard>) {
        this.magicCards.addAll(cards)
    }
}