package com.zachtib.spoilers.test

import com.zachtib.spoilers.SpoilersApp
import com.zachtib.spoilers.dagger.DaggerTestComponent
import com.zachtib.spoilers.dagger.ScryfallTestModule
import com.zachtib.spoilers.dagger.TestComponent
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class InjectionTests {
    lateinit var app: SpoilersApp

    @Before
    fun setup() {
        app = DaggerTestComponent.builder()
            .scryfall(ScryfallTestModule.empty())
            .build()
            .createApp()
    }

    @Test
    fun `test app was created`() {
        assertTrue(this::app.isInitialized)
    }
}