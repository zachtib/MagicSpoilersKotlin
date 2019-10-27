package com.zachtib.spoilers.scryfall

import com.zachtib.spoilers.models.CardFace
import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import java.time.LocalDate

fun ScryfallSet.toMagicSet() = MagicSet(
    name = name,
    code = code,
    scryfallId = id,
    releaseDate = releasedAt?.let { dateString ->
        val (year, month, day) = dateString.split('-').map { it.toInt() }
        LocalDate.of(year, month, day)
    },
    watched = false
)

fun ScryfallCard.toMagicCard(): MagicCard {
    return MagicCard(
        scryfallId = id,
        name = name,
        cmc = cmc,
        colors = colors ?: emptyList(),
        colorIndicator = colorIndicator,
        colorIdentity = colorIdentity,
        cardFaces = cardFaces?.map { it.toCardFace() },
        manaCost = manaCost ?: "",
        typeLine = typeLine,
        oracleText = oracleText ?: "",
        power = power,
        toughness = toughness,
        loyalty = loyalty,
        setCode = "",
        imageUri = images?.png
    )
}

fun ScryfallCardFace.toCardFace(): CardFace {
    return CardFace(
        name = name,
        manaCost = manaCost ?: "",
        colors = colors ?: emptyList(),
        colorIndicator = colorIndicator,
        typeLine = typeLine,
        oracleText = oracleText ?: "",
        power = power,
        toughness = toughness,
        loyalty = loyalty,
        imageUri = imageUris?.png
    )
}