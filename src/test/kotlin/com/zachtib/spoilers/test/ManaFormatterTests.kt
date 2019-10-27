package com.zachtib.spoilers.test

import com.zachtib.spoilers.formatting.formatMana
import org.junit.Assert.assertEquals
import org.junit.Test

class ManaFormatterTests {

    companion object {
        fun assertManaEquals(input: String, expected: String) {
            val actual = input.formatMana()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `test simple formatting`() {
        assertManaEquals("{w}", ":mana-w:")
    }

    @Test
    fun `test lower case formatting`() {
        assertManaEquals("{W}", ":mana-w:")
    }

    @Test
    fun `test string without mana`() {
        assertManaEquals("Hello, World", "Hello, World")
    }

    @Test
    fun `test complex string without mana`() {
        val string = "Pay 1 life: Draw a card\nPay 2 life: Draw 2 cards"
        assertManaEquals(string, string)
    }

    @Test
    fun `test complex string with mana`() {
        val input = "{T}: Add {G} to your mana pool"
        val expected = ":mana-t:: Add :mana-g: to your mana pool"
        assertManaEquals(input, expected)
    }

    @Test
    fun `test hybrid mana formatting`() {
        assertManaEquals("{W/U}", ":mana-wu:")
    }

    @Test
    fun `test complex single and hybrid cost`() {
        assertManaEquals("{2}{R/G}", ":mana-2::mana-rg:")
    }
}