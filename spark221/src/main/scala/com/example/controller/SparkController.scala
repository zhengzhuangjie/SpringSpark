package com.example.controller

import java.util.List

import com.example.domain.User
import com.example.service.UserQuery
import com.example.spark.Demo
import org.apache.spark.examples.ml.DecisionTreeExample
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import scala.language.implicitConversions

@RestController
@RequestMapping(Array("/spark"))
class SparkController @Autowired()(private val modelQuery: UserQuery) {


  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def home(): String = {
    "Hello World!"
  }

  @RequestMapping(value = Array("/getUser"), method = Array(RequestMethod.GET))
  def getUser(): String = {
    val user = new User
    user.id = 110
    user.name = "jack"
    user.toString
  }

  @GetMapping(value = Array("/findById/{id}"))
  def findById(@PathVariable(value = "id") id: Long): User = {
    modelQuery.findOne(id)
  }

  @GetMapping(value = Array("/findByName/{name}"))
  def findByName(@PathVariable(value = "name") name: String): List[User] = {
    modelQuery.findByName(name)
  }

  @RequestMapping(value = Array("/pi"), method = Array(RequestMethod.GET))
  def startPi(): String = {
    val args = Array[String]()
    Demo.pi(args)
    "success"
  }

  @RequestMapping(value = Array("/dt"), method = Array(RequestMethod.GET))
  def startDecisionTree(@RequestParam("value") value: String): String = {
    val args = value.split(" ")
    DecisionTreeExample.init(args)
    "success"
  }
}
