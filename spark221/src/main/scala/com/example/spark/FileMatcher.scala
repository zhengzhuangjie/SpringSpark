package com.example.spark

import java.io.File

object FileMatcher {
  private def filesHere = (new File("/home/hadoop")).listFiles()

  def filesMatching(query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }

  def filesEnding(query: String) =
    filesMatching(query, _.endsWith(_))

  def filesContaining(query: String) =
    filesMatching(query, _.contains(_))

  def filesRegex(query: String) =
    filesMatching(query, _.matches(_))

  def main(args: Array[String]): Unit = {
    for (file <- filesContaining("sa"))
      println(file)
  }
}
