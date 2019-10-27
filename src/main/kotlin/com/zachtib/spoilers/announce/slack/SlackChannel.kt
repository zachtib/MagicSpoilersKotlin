package com.zachtib.spoilers.announce.slack

import com.zachtib.spoilers.announce.AnnounceChannel
import com.zachtib.spoilers.announce.slack.models.*
import com.zachtib.spoilers.formatting.formatAsString
import com.zachtib.spoilers.formatting.formatManaCosts
import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import javax.inject.Inject

class SlackChannel @Inject constructor(
    private val webhook: SlackWebhook,
    private val config: SlackConfigService
) : AnnounceChannel {

    override suspend fun announceExpansions(magicSets: List<MagicSet>) {
        for (set in magicSets) {
            postFormattedMessage(
                SlackMessage(
                    text = "New set added: ${set.name}"
                )
            )
        }
    }

    override suspend fun announceCards(cards: List<MagicCard>) {
        for (card in cards) {
            val blocks = mutableListOf<Any>()
            val formattedCard = card.formatManaCosts()
            if (formattedCard.cardFaces != null && formattedCard.cardFaces.isNotEmpty()) {
                for (face in formattedCard.cardFaces) {
                    if (face.imageUri != null) {
                        blocks.add(SectionWithImage(
                            text = SectionText(face.formatAsString()),
                            accessory = ImageBlock(
                                image_url = face.imageUri,
                                alt_text = card.name
                            )
                        ))
                    } else {
                        blocks.add(SectionText(face.formatAsString()))
                    }
                }
            } else {
                if (card.imageUri != null) {
                    blocks.add(SectionWithImage(
                        text = SectionText(formattedCard.formatAsString()),
                        accessory = ImageBlock(
                            image_url = card.imageUri,
                            alt_text = card.name
                        )
                    ))
                } else {
                    blocks.add(SectionText(formattedCard.formatAsString()))
                }
            }
            postFormattedMessage(
                SlackMessage(
                    text = card.name,
                    blocks = blocks
                )
            )
        }
    }

    private suspend fun postFormattedMessage(message: SlackMessage) {
        val configs = config.getConfigs()
        for (config in configs) {
            val (p1, p2, p3) = config.auth.split('/')
            val updatedMessage = message.copy(channel = config.channel)
            webhook.postMessage(p1, p2, p3, updatedMessage)
        }
    }
}