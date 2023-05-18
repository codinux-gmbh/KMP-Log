package net.codinux.log

import net.codinux.log.appender.Appender
import kotlin.jvm.JvmStatic
import kotlin.reflect.KClass

object LoggerFactory {

    /**
     * The default log level of the logging system that will be used if no logger specific level is set with [LoggerBase.level].
     *
     * If slf4j is on the classpath setting this value has no effect. Configure log level via logging backend (logback, log4j, ...) then.
     */
    var DefaultLevel: LogLevel = LogLevel.Info

    private var factory: ILoggerFactory = Platform.createDefaultLoggerFactory()

    @JvmStatic
    fun setLoggerFactory(factory: ILoggerFactory) {
        this.factory = factory
    }

    @JvmStatic
    fun addAppender(appender: Appender) {
        this.factory.addAppender(appender)
    }


    @JvmStatic
    fun getLogger(name: String): Logger {
        return factory.getLogger(name)
    }

    @JvmStatic
    fun getLogger(forClass: KClass<*>): Logger =
        getLogger(Platform.getLoggerName(forClass))

}