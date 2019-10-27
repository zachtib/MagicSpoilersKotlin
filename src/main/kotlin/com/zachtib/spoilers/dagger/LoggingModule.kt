package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.logging.LogLevel
import com.zachtib.spoilers.logging.Logger
import com.zachtib.spoilers.logging.StdOutLogger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoggingModule(private val level: LogLevel) {
    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return StdOutLogger(level)
    }
}