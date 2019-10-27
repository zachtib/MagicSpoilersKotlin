package com.zachtib.spoilers.test

import com.zachtib.spoilers.logging.LogLevel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LogLevelTests {
    @Test
    fun `test log level from string`() {
        val debug = LogLevel.valueOf("DEBUG")
        assertEquals(LogLevel.DEBUG, debug)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test log level from empty string`() {
        LogLevel.valueOf("")
    }

    @Test
    fun `test debug to info comparison`() {
        assertTrue(LogLevel.DEBUG < LogLevel.INFO)
    }

    @Test
    fun `test info to warn comparison`() {
        assertTrue(LogLevel.INFO < LogLevel.WARN)
    }

    @Test
    fun `test warn to error comparison`() {
        assertTrue(LogLevel.WARN < LogLevel.ERROR)
    }
}