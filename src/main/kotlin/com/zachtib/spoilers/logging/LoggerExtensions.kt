package com.zachtib.spoilers.logging

import okhttp3.logging.HttpLoggingInterceptor

fun Logger.getOkHttpLogger(level: LogLevel = LogLevel.DEBUG) = object : HttpLoggingInterceptor.Logger {
    override fun log(message: String) = when (level) {
        LogLevel.DEBUG -> debug(message)
        LogLevel.INFO -> info(message)
        LogLevel.WARN -> warn(message)
        LogLevel.ERROR -> error(message)
    }
}