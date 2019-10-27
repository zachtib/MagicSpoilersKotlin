package com.zachtib.spoilers.logging

class StdOutLogger(var level: LogLevel) : Logger {

    override fun debug(message: String) = log(LogLevel.DEBUG, message)

    override fun info(message: String) = log(LogLevel.INFO, message)

    override fun warn(message: String) = log(LogLevel.WARN, message)

    override fun error(message: String) = log(LogLevel.ERROR, message)

    private fun log(messageLevel: LogLevel, message: String, throwable: Throwable? = null) {
        if (messageLevel >= this.level) {
            println("[${messageLevel.name}] $message")
            throwable?.printStackTrace()
        }
    }
}