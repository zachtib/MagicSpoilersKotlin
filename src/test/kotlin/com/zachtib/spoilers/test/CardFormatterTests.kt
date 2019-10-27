package com.zachtib.spoilers.test

import com.zachtib.spoilers.formatting.formatAsString
import com.zachtib.spoilers.formatting.formatManaCosts
import com.zachtib.spoilers.models.MagicCard
import org.junit.Assert.assertEquals
import org.junit.Test

class CardFormatterTests {

    companion object {
        val basicPlains = MagicCard(
            "",
            "Plains",
            0F,
            listOf(),
            null,
            listOf(),
            null,
            "",
            "Basic Land - Plains",
            "({T}: Add {W}.)",
            null,
            null,
            null,
            "",
            null
        )
    }

    @Test
    fun formatSimpleCard() {
        val expected = "Plains \n" +
                "Basic Land - Plains\n" +
                "({T}: Add {W}.)\n" +
                ""
        assertEquals(expected, basicPlains.formatAsString())
    }

    @Test
    fun `format plains with mana`() {
        val expected = "Plains \n" +
                "Basic Land - Plains\n" +
                "(:mana-t:: Add :mana-w:.)\n" +
                ""
        assertEquals(expected, basicPlains.formatManaCosts().formatAsString())
    }
}