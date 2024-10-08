package net.codinux.log.appender

import net.codinux.log.LogLevel

interface AppenderContainer {

    val doesAnyAppenderLogThreadName: Boolean

    fun addAppender(appender: Appender)

    fun getAppenders(): Collection<Appender>

    fun appendToAppenders(level: LogLevel, loggerName: String, message: String, exception: Throwable? = null)

}