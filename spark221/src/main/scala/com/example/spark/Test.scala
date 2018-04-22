package com.example.spark

import scala.io.Source

object Test {
  val filesHere = (new java.io.File("/home/hadoop")).listFiles

  def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines.toList

  def grep(pattern: String) =
    for (
      file <- filesHere
      if file.getName.endsWith("sample_libsvm_data.txt");
      line <- fileLines(file)
      if line.trim.contains(pattern)
    ) println(line.trim)

  def grep2(pattern: String) =
    for {
      file <- filesHere
      if file.getName.endsWith("sample_libsvm_data.txt")
      line <- fileLines(file)
      if line.trim.contains(pattern)
    } println(line.trim)

  /**
    * 在for表达式之前加上关键字yield，可以创建一个值去记住每一次迭代，这个将返回Array[File]
    * 语法：
    * for{子句} yield{循环体}
    *
    * @return
    */
  def scalaFiles =
    for (
      file <- filesHere
      if (file.getName.endsWith(".txt"))
    ) yield file

  def main(args: Array[String]): Unit = {

    var a = 1;
    {
      var a = 2
      println(a)
    }
    println(a)

    val firstArg = if (args.length > 0) args(0) else ""
    firstArg match {
      case "salt" => println("pepper")
      case "chips" => println("salsa")
      case "eggs" => println("bacon")
      case _ => println("huh?")
    }

    val friend = firstArg match {
      case "salt" => "pepper"
      case "chips" => "salsa"
      case "eggs" => "bacon"
      case _ => "huh?"
    }
    println("+++" + friend)

    grep2("168")
    println(scalaFiles.length)
    for (file <- scalaFiles)
      println(file)

    val forLineLengths =
      for {
        file <- filesHere
        if (file.getName.endsWith("*.txt"))
        line <- fileLines(file)
        trimmed = line.trim
        if (trimmed.contains("168"))
      } yield trimmed.length
    println(forLineLengths.length)
    for (l <- forLineLengths)
      println(l)

    for (i <- 1 to 10)
      print(i + " ")
    println()
    for (i <- 1 until 10)
      print(i + " ")
    println()
    seperate()

    val numbers = Seq(11, 2, 5, 1, 6, 3, 9)
    println(numbers.min)
    println(numbers.max)
    println(numbers.filter(n => n % 2 == 0))
    println(numbers.filterNot(n => n % 2 == 0))
    seperate()

    case class Book(title: String, pages: Int)
    val books = Seq(Book("1", 85), Book("2", 240), Book("4", 130), Book("3", 495))
    println(books.maxBy(book => book.pages))
    println(books.minBy(book => book.pages))
    println(books.filter(book => book.pages >= 120))
    seperate()

    val ab = Seq('a', 'b')
    val cd = Seq('c', 'd')
    val ef = Seq('e', 'f')
    val abcdef = Seq(ab, cd, ef)
    println(abcdef.flatten)
    seperate()

    val num1 = Seq(1, 2, 3, 4, 5, 6)
    val num2 = Seq(4, 5, 6, 7, 8, 9)
    //List(1, 2, 3)
    println(num1.diff(num2))
    //List(4, 5, 6)
    println(num1.intersect(num2))
    //List(1, 2, 3, 4, 5, 6, 4, 5, 6, 7, 8, 9)
    println(num1.union(num2))
    seperate()

    //ture
    println(numbers.forall(n => n < 20))
    //false
    println(numbers.forall(n => n > 5))
    seperate()

    println(numbers.partition(n => n % 2 == 0))
    //-1是初始值，第二个括号是对每个值进行操作
    println(numbers.foldLeft(-1)((res, n) => res + n))

    for (i <- 1 to 100) {
      i match {
        case 10 => println(10)
        case 50 => println(50)
        case 80 => println(80)
        case _ if (i % 4 == 0) => println(i + ":能被4整除")
        case _ if (i % 3 == 0) => println(i + ":能被3整除")
        case _ =>
      }
    }

    val onetwo = List(1, 2)
    val threefour = List(3, 4)
    val ottf = onetwo ::: threefour
    println((ottf))
    val ott = 1 :: onetwo
    println(ott)
    seperate()

    val tuple = Tuple3("one", 1, '1')
    println(tuple._3)
    println(tuple._1)
    seperate()

    for (line <- Source.fromFile("/home/hadoop/coins2.json").getLines())
      println(line)

  }

  def seperate(): Unit = {
    println("----------")
  }

  def seperate(flag: String): Unit = {

  }
}
