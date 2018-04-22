package com.example.demo.excp

object ExcUtil {
  /**
    * 这个抛出异常
    *
    * @param i
    * @return
    */
  def failingFn(i: Int): Int = {
    val y: Int = throw new Exception("fail")
    try {
      val x = 42 + 5
      x + y
    }
    catch {
      case e: Exception => 43
    }
  }

  /**
    * 这个返回43
    *
    * @param i
    * @return
    */
  def failingFn2(i: Int): Int = {
    try {
      val x = 42 + 5
      x + ((throw new Exception("fail")): Int)
    }
    catch {
      case e: Exception => 43
    }
  }

  def main(args: Array[String]): Unit = {
    println(failingFn2(12))
  }
}
