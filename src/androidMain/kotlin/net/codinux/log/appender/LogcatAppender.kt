package net.codinux.log.appender

import android.util.Log
import net.codinux.log.LogLevel

class LogcatAppender : Appender {

  companion object {

    val Default = LogcatAppender()

  }


  override fun append(level: LogLevel, loggerName: String, message: String, exception: Throwable?) {
    when (level) {
      LogLevel.Fatal, LogLevel.Error -> Log.e(loggerName, message, exception)
      LogLevel.Warn -> Log.w(loggerName, message, exception)
      LogLevel.Info -> Log.i(loggerName, message, exception)
      LogLevel.Debug -> Log.d(loggerName, message, exception)
      LogLevel.Trace -> Log.v(loggerName, message, exception)
      LogLevel.Off -> { }
    }
  }

}