package net.codinux.log.appender

import net.codinux.log.LogLevel
import net.codinux.log.Platform

open class MessageFormatter {

    private val lineSeparator = Platform.lineSeparator()

    open fun formatMessage(message: String, exception: Throwable? = null): String {
        return if (exception != null) {
            "$message:$lineSeparator${exception.stackTraceToString()}"
        } else {
            message
        }
    }

    open fun formatMessage(level: LogLevel, message: String, loggerName: String, threadName: String? = null, exception: Throwable? = null): String {
        // may add format specifiers as logback has:
        // %d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c{3.}] (%t) %s%e%n
        // p = level
        // c = category name (= logger name)
        // t = thread name
        // s = Simple message (Renders just the log message, with no exception trace)
        // e = Exception
        // n = Newline

        // Default format of most logging implementations:
        // 19:10:48.547 [Test worker] INFO net.codinux.log.slf4j.LogbackBindingTest - Just a test
        // java.lang.Exception: No animals have been harmed
        //   at net.codinux.log.slf4j.LogbackBindingTest.simpleLoggerOutput(LogbackBindingTest.kt:11)
        //   at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ...
        // be aware that we should localize time!
        return "${threadName?.let { "[$it] " } ?: ""}$level $loggerName - ${formatMessage(message, exception)}"
    }

}