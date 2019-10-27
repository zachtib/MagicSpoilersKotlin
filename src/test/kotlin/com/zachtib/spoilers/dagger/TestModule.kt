package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.announce.AnnounceChannel
import com.zachtib.spoilers.announce.TestAnnounceChannel
import com.zachtib.spoilers.database.InMemorySpoilersDatabase
import com.zachtib.spoilers.database.SpoilersDatabase
import com.zachtib.spoilers.logging.Logger
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module(includes = [TestModule.Bindings::class])
class TestModule {
    @Provides
    @Singleton
    fun provideLogger(): Logger = object : Logger {
        override fun debug(message: String) = Unit
        override fun info(message: String) = Unit
        override fun warn(message: String) = Unit
        override fun error(message: String) = Unit
    }

    @Module
    interface Bindings {
        @Binds
        fun bindDatabase(database: InMemorySpoilersDatabase): SpoilersDatabase

        @Binds
        @IntoSet
        fun bindAnnounceChannel(channel: TestAnnounceChannel): AnnounceChannel
    }
}