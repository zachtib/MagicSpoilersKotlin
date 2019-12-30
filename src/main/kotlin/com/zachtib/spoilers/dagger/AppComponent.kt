package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.SpoilersApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HttpModule::class,
        JasyncModule::class,
        LoggingModule::class,
        ScryfallModule::class,
        SlackModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun jasync(module: JasyncModule): Builder
        fun logging(module: LoggingModule): Builder

        fun build(): AppComponent
    }

    fun createApp(): SpoilersApp
}