package com.zachtib.spoilers

import com.zachtib.spoilers.dagger.DaggerAppComponent
import com.zachtib.spoilers.dagger.MongoDbModule
import com.zachtib.spoilers.dagger.LoggingModule
import com.zachtib.spoilers.logging.LogLevel

fun main(args: Array<String>) {
    val mongoDbConnectionString = System.getenv("MONGODB_URI") + "?retryWrites=false"
    val logLevel = LogLevel.valueOf(System.getenv("LOG_LEVEL") ?: "WARN")
    val component = DaggerAppComponent.builder()
        .mongo(MongoDbModule(mongoDbConnectionString))
        .logging(LoggingModule(logLevel))
        .build()

    val app = component.createApp()

    app.handleCommandLine(args)
}