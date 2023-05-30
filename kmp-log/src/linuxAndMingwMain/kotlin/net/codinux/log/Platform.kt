package net.codinux.log

import net.codinux.log.appender.Appender
import net.codinux.log.appender.ConsoleAppender
import kotlin.reflect.KClass

actual class Platform {

  actual companion object {

    actual fun createDefaultLoggerFactory(): ILoggerFactory = DefaultLoggerFactory()

    actual val systemDefaultAppender: Appender = ConsoleAppender.Default

    actual fun <T : Any> getLoggerName(forClass: KClass<T>) =
      Platform.getLoggerNameForKClassesWithQualifiedName(forClass)

  }

}