package net.codinux.log

import net.codinux.log.appender.Appender
import net.codinux.log.appender.JsConsoleAppender
import kotlin.reflect.KClass

actual class SystemDefaults {

  actual companion object {

    private val defaultAppender = JsConsoleAppender()

    actual fun createDefaultLoggerFactory(): ILoggerFactory = DefaultLoggerFactory()

    actual fun getDefaultAppender(): Appender = defaultAppender

    actual fun <T : Any> getLoggerName(forClass: KClass<T>): String {
      // unwrapping companion objects is not possible on JS. There as class / logger name "Companion" will be used
      // do not use forClass.qualifiedName on JS, it will produce an error
      return forClass.js.name
    }

  }

}