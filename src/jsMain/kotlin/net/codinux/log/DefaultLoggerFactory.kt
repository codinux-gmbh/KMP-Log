package net.codinux.log

actual class DefaultLoggerFactory actual constructor() {

  actual fun createDefaultLoggerFactory(): ILoggerFactory = JsConsoleLoggerFactory()

}