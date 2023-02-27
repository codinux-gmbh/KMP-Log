package net.codinux.log

import kotlin.reflect.KClass

actual fun <T : Any> getLoggerName(forClass: KClass<T>): String {
  println(forClass.toString())
  println(forClass.qualifiedName)
  println(forClass.simpleName)

  forClass.qualifiedName?.let { qualifiedName ->
    // unwrap companion object
    return if (qualifiedName.endsWith(".Companion")) { // ok, someone could name a class 'Companion', but in this i have no pity that his/her logger name is wrong then
      qualifiedName.substring(0, qualifiedName.length - ".Companion".length)
    } else {
      qualifiedName
    }
  }

  forClass.simpleName?.let {
    return it
  }

  val asString = forClass.toString()
  return if (asString.startsWith("class ")) { // remove 'class ' from beginning to .toString() return value
    asString.substring("class ".length)
  } else {
    asString
  }
}