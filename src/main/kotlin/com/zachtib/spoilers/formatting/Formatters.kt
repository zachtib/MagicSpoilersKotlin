package com.zachtib.spoilers.formatting

import com.zachtib.spoilers.models.CardFace
import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.ManaCost
import java.text.Format
import java.util.*

//p = re.compile(r'{(\w+)}')
//hybrid = re.compile(r'{(\w+)/(\w+)}')
//
//
//def format_mana(manacost: str) -> str:
//phase1 = hybrid.sub(r':mana-\1\2:', manacost)
//return p.sub(r":mana-\1:", phase1)

object Formatters {
    val regularMana = """\{(\w+)}""".toRegex()
    val hybridMana = """\{(\w+)/(\w+)}""".toRegex()
}

fun MagicCard.bottomCorner(): String? {
    return if (power != null && toughness != null) "$power/$toughness" else loyalty
}

fun CardFace.bottomCorner(): String? {
    return if (power != null && toughness != null) "$power/$toughness" else loyalty
}

fun String.formatMana(): String {
    return Formatters.hybridMana.replace(this) { matchResult: MatchResult ->
        val (first, second) = matchResult.destructured
        ":mana-${first.toLowerCase()}${second.toLowerCase()}:"
    }.let {
        Formatters.regularMana.replace(it) { matchResult ->
            val mana = matchResult.destructured.component1()
            ":mana-${mana.toLowerCase()}:"
        }
    }
}

fun MagicCard.formatAsString() = buildString {
    appendln("$name $manaCost")
    if (cardFaces == null || cardFaces.isEmpty()) {
        appendln(typeLine)
        appendln(oracleText)
        bottomCorner()?.let {
            appendln(it)
        }
    } else {
        cardFaces.forEach { face ->
            append(face.formatAsString())
        }
    }
}

fun CardFace.formatAsString() = buildString {
    appendln("$name $manaCost")
    appendln(typeLine)
    appendln(oracleText)
    bottomCorner()?.let {
        appendln(it)
    }
}

fun MagicCard.formatManaCosts() = copy(
    scryfallId = "", // Clear this scryfallId as we're reformatting fields here, just in case
    manaCost = manaCost.formatMana(),
    oracleText = oracleText.formatMana(),
    cardFaces = cardFaces?.map { face -> face.formatManaCosts() }
)

fun CardFace.formatManaCosts() = copy(
    manaCost = manaCost.formatMana(),
    oracleText = oracleText.formatMana()
)