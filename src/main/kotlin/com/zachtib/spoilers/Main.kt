package com.zachtib.spoilers

import com.zachtib.spoilers.dagger.DaggerAppComponent
import com.zachtib.spoilers.dagger.JasyncModule
import com.zachtib.spoilers.dagger.LoggingModule
import com.zachtib.spoilers.logging.LogLevel

fun parseDatabaseUrl(url: String): List<String> {
    val regex = Regex("postgres://(\\w+):(\\w+)@([\\w-.]+):(\\d+)/(\\w+)")
    println(regex.matches(url))
    regex.matchEntire(url)?.let { result ->
        val (username, password, host, port, database) = result.destructured
        return listOf(username, password, host, port, database)
    }
    return listOf()
}

fun main(args: Array<String>) {
    val databaseUrl = System.getenv("DATABASE_URL") ?: throw RuntimeException("Required variable, DATABASE_URL, is not defined")
    val (username, password, host, port, database) = parseDatabaseUrl(databaseUrl)
    val logLevel = LogLevel.valueOf(System.getenv("LOG_LEVEL") ?: "WARN")
    val component = DaggerAppComponent.builder()
        .jasync(JasyncModule(host, port, database, username, password))
        .logging(LoggingModule(logLevel))
        .build()

    val app = component.createApp()

    app.handleCommandLine(args)
}