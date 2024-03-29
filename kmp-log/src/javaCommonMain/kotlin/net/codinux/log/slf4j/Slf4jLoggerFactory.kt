package net.codinux.log.slf4j

import net.codinux.log.LoggerFactoryBase
import net.codinux.log.slf4j.binding.Log4j2Configurator
import org.slf4j.LoggerFactory

open class Slf4jLoggerFactory : LoggerFactoryBase() {

    protected open val log4j2Configurator: Log4j2Configurator by lazy { Log4j2Configurator() }


    init {
        if (Slf4jUtil.boundLoggingFramework == Slf4jBinding.Log4j2) {
            log4j2Configurator.setRootLevel(net.codinux.log.LoggerFactory.DefaultLevel)
        }
    }


    override fun createLogger(name: String) =
        Slf4jLogger(name, this)

}