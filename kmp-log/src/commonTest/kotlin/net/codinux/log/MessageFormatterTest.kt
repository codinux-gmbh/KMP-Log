package net.codinux.log

import net.codinux.log.appender.MessageFormatter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MessageFormatterTest {

    companion object {
        private const val Message = "Test Message"

        private const val ExceptionMessage = "Just a test, no animals have been harmed"

        private val DefaultLevel = LogLevel.Info

        private const val LoggerName = "com.example.TestLogger"

        private const val ThreadName = "WorkerThread #1"
    }


    private val underTest = MessageFormatter()


    @Test
    fun messageWithoutException() {
        val result = underTest.formatMessage(Message, null)

        assertEquals(result, Message)
    }

    @Test
    fun messageWithUncheckedException() {
        val result = underTest.formatMessage(Message, IllegalArgumentException(ExceptionMessage))

        assertTrue(result.contains(Message))
        assertTrue(result.contains("IllegalArgumentException"))
        assertTrue(result.contains(ExceptionMessage))
    }

    @Test
    fun messageWithCheckedException() {
        val result = underTest.formatMessage(Message, Error(ExceptionMessage))

        assertTrue(result.contains(Message))
        assertTrue(result.contains("Error"))
        assertTrue(result.contains(ExceptionMessage))
    }


    @Test
    fun loggerName() {
        val result = underTest.formatMessage(DefaultLevel, Message, LoggerName)

        assertTrue(result.contains(LoggerName))
    }


    @Test
    fun withoutThreadName() {
        val result = underTest.formatMessage(DefaultLevel, Message, LoggerName, null)

        assertFalse(result.contains(ThreadName))
    }

    @Test
    fun withThreadName() {
        val result = underTest.formatMessage(DefaultLevel, Message, LoggerName, ThreadName)

        assertTrue(result.contains(ThreadName))
    }


    @Test
    fun levelTrace() {
        val result = underTest.formatMessage(LogLevel.Trace, Message, LoggerName)

        assertTrue(result.contains(LogLevel.Trace.name))
        assertFalse(result.contains(LogLevel.Debug.name))
        assertFalse(result.contains(LogLevel.Info.name))
        assertFalse(result.contains(LogLevel.Warn.name))
        assertFalse(result.contains(LogLevel.Error.name))
    }

    @Test
    fun levelDebug() {
        val result = underTest.formatMessage(LogLevel.Debug, Message, LoggerName)

        assertFalse(result.contains(LogLevel.Trace.name))
        assertTrue(result.contains(LogLevel.Debug.name))
        assertFalse(result.contains(LogLevel.Info.name))
        assertFalse(result.contains(LogLevel.Warn.name))
        assertFalse(result.contains(LogLevel.Error.name))
    }

    @Test
    fun levelInfo() {
        val result = underTest.formatMessage(LogLevel.Info, Message, LoggerName)

        assertFalse(result.contains(LogLevel.Trace.name))
        assertFalse(result.contains(LogLevel.Debug.name))
        assertTrue(result.contains(LogLevel.Info.name))
        assertFalse(result.contains(LogLevel.Warn.name))
        assertFalse(result.contains(LogLevel.Error.name))
    }

    @Test
    fun levelWarn() {
        val result = underTest.formatMessage(LogLevel.Warn, Message, LoggerName)

        assertFalse(result.contains(LogLevel.Trace.name))
        assertFalse(result.contains(LogLevel.Debug.name))
        assertFalse(result.contains(LogLevel.Info.name))
        assertTrue(result.contains(LogLevel.Warn.name))
        assertFalse(result.contains(LogLevel.Error.name))
    }

    @Test
    fun levelError() {
        val result = underTest.formatMessage(LogLevel.Error, Message, LoggerName)

        assertFalse(result.contains(LogLevel.Trace.name))
        assertFalse(result.contains(LogLevel.Debug.name))
        assertFalse(result.contains(LogLevel.Info.name))
        assertFalse(result.contains(LogLevel.Warn.name))
        assertTrue(result.contains(LogLevel.Error.name))
    }

}