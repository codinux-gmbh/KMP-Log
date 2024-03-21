package net.codinux.log

import kotlin.reflect.KClass

object Log {

    inline fun trace(exception: Throwable? = null, loggerName: String? = null, crossinline messageSupplier: () -> String) =
        LoggerFactory.getLogger(loggerName).trace(exception) { messageSupplier() }

    inline fun <reified T : Any> trace(exception: Throwable? = null, loggerClass: KClass<T> = T::class, crossinline messageSupplier: () -> String) =
        trace(exception, Platform.getLoggerName(loggerClass), messageSupplier)


    inline fun debug(exception: Throwable? = null, loggerName: String? = null, crossinline messageSupplier: () -> String) =
        LoggerFactory.getLogger(loggerName).debug(exception) { messageSupplier() }

    inline fun <reified T : Any> debug(exception: Throwable? = null, loggerClass: KClass<T> = T::class, crossinline messageSupplier: () -> String) =
        debug(exception, Platform.getLoggerName(loggerClass), messageSupplier)


    inline fun info(exception: Throwable? = null, loggerName: String? = null, crossinline messageSupplier: () -> String) =
        LoggerFactory.getLogger(loggerName).info(exception) { messageSupplier() }

    inline fun <reified T : Any> info(exception: Throwable? = null, loggerClass: KClass<T> = T::class, crossinline messageSupplier: () -> String) =
        info(exception, Platform.getLoggerName(loggerClass), messageSupplier)


    inline fun warn(exception: Throwable? = null, loggerName: String? = null, crossinline messageSupplier: () -> String) =
        LoggerFactory.getLogger(loggerName).warn(exception) { messageSupplier() }

    inline fun <reified T : Any> warn(exception: Throwable? = null, loggerClass: KClass<T> = T::class, crossinline messageSupplier: () -> String) =
        warn(exception, Platform.getLoggerName(loggerClass), messageSupplier)


    inline fun error(exception: Throwable? = null, loggerName: String? = null, crossinline messageSupplier: () -> String) =
        LoggerFactory.getLogger(loggerName).error(exception) { messageSupplier() }

    inline fun <reified T : Any> error(exception: Throwable? = null, loggerClass: KClass<T> = T::class, crossinline messageSupplier: () -> String) =
        error(exception, Platform.getLoggerName(loggerClass), messageSupplier)

}