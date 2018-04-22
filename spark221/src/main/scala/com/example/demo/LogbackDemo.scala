package com.example.demo

import ch.qos.logback.classic.{BasicConfigurator, Logger, LoggerContext}
import ch.qos.logback.core.ConsoleAppender

object LogbackDemo {
  def main(args: Array[String]): Unit = {
    val loggerContext: LoggerContext = new LoggerContext()
    val logger: Logger = loggerContext.getLogger(LogbackDemo.getClass)
    val basicConfigurator: BasicConfigurator = new BasicConfigurator
    basicConfigurator.configure(loggerContext)
    //    logger.addAppender(new ConsoleAppender())
    logger.info("hello world")
  }
}
