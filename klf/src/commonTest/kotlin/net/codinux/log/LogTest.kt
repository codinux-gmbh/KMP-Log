package net.codinux.log

import net.codinux.log.test.TestPlatform
import net.codinux.log.test.WatchableAppender
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class LogTest {

    companion object {

        private val appender: WatchableAppender

        init {
            // otherwise on JVM slf4j's org.slf4j.helpers.NOPLoggerFactory is used. Loggers then have the name "NOP"
            LoggerFactory.setLoggerFactory(DefaultLoggerFactory())

            LoggerFactory.RootLevel = LogLevel.Trace // so that by default all logs get written

            appender = WatchableAppender().apply {
                LoggerFactory.addAppender(this)
            }
        }
    }


    private val message = "Log message"

    private val loggerName: String = if (TestPlatform.isRunningInJs) LogTest::class.simpleName!! else "net.codinux.log.LogTest"

    private val exception = IllegalArgumentException("Just a test")


    @BeforeTest
    fun setUp() {
        appender.reset()
    }


    @Test
    fun infoWithGenericTyp() {
        Log.info<LogTest> { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Info, message, loggerName))
    }

    @Test
    fun infoWithGenericTypAndException() {
        Log.info<LogTest>(exception) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Info, message, loggerName, exception = exception))
    }

    @Test
    fun infoWithLoggerClass() {
        Log.info(loggerClass = LogTest::class) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Info, message, loggerName))
    }

    @Test
    fun infoWithLoggerClassAndException() {
        Log.info(exception, LogTest::class) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Info, message, loggerName, exception = exception))
    }

    @Test
    fun infoWithLoggerName() {
        Log.info(loggerName = loggerName) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Info, message, loggerName))
    }

    @Test
    fun infoWithoutLoggerName() {
        LoggerFactory.defaultLoggerName = "app"

        Log.info { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Info, message, "app"))
    }


    @Test
    fun errorWithGenericTypAndException() {
        Log.error<LogTest>(exception) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Error, message, loggerName, exception = exception))
    }

    @Test
    fun warnWithGenericTypAndException() {
        Log.warn<LogTest>(exception) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Warn, message, loggerName, exception = exception))
    }

    @Test
    fun debugWithGenericTypAndException() {
        Log.debug<LogTest>(exception) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Debug, message, loggerName, exception = exception))
    }

    @Test
    fun traceWithGenericTypAndException_LevelTraceEnabled() {
        Log.trace<LogTest>(exception) { message }

        assertTrue(appender.hasExactlyOneLogEventWith(LogLevel.Trace, message, loggerName, exception = exception))
    }

    @Test
    fun traceWithGenericTypAndException_LevelTraceDisabled() {
        LoggerFactory.RootLevel = LogLevel.Debug

        Log.trace<LogTest>(exception) { message }

        assertTrue(appender.hasNoLogEvents)
    }

}