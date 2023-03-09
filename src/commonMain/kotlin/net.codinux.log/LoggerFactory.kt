package net.codinux.log

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

object LoggerFactory {

    private var factory: ILoggerFactory = DefaultLoggerFactory().createDefaultLoggerFactory()

    fun setLoggerFactory(factory: ILoggerFactory) {
        this.factory = factory
    }


    inline fun <reified R : Any> logger() = LoggerDelegate<R>()

    fun getLogger(name: String): Logger {
        return factory.getLogger(name)
    }

    fun getLogger(forClass: KClass<*>): Logger =
        getLogger(getLoggerName(forClass))


    class LoggerDelegate<in R : Any> : ReadOnlyProperty<R, Logger> {
        override fun getValue(thisRef: R, property: KProperty<*>) = LoggerFactory.getLogger(thisRef::class)
    }

}