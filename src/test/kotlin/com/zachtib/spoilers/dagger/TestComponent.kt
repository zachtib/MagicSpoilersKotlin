package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.SpoilersApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ScryfallTestModule::class,
        TestModule::class
    ]
)
interface TestComponent {
    @Component.Builder
    interface Builder{
        fun scryfall(module: ScryfallTestModule): Builder

        fun build(): TestComponent
    }

    fun createApp(): SpoilersApp
}