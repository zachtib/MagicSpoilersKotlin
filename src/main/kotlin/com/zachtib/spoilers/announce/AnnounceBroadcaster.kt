package com.zachtib.spoilers.announce

import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnnounceBroadcaster @Inject constructor(
    private val channels: Set<@JvmSuppressWildcards AnnounceChannel>
) {

    suspend fun announceExpansions(magicSets: List<MagicSet>) {
        channels.forEach {
            it.announceExpansions(magicSets)
        }
    }

    suspend fun announceCards(cards: List<MagicCard>) {
        channels.forEach {
            it.announceCards(cards)
        }
    }

}
