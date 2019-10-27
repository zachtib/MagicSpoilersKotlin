package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.SpoilersApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HttpModule::class,
        MongoDbModule::class,
        LoggingModule::class,
        ScryfallModule::class,
        SlackModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun mongo(module: MongoDbModule): Builder
        fun logging(module: LoggingModule): Builder

        fun build(): AppComponent
    }

    fun createApp(): SpoilersApp
}