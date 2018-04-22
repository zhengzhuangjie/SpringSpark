package com.example.demo

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import akka.pattern.ask


class ScalaAskExamplesTest extends FunSpecLike with Matchers {


  val system = ActorSystem()
  implicit val timeout = Timeout(5, TimeUnit.MICROSECONDS)
  val pongActor = system.actorOf(Props(classOf[ScalaPongActor]))

  def askPong(message: String): Future[String] = (pongActor ? message).mapTo[String]

  describe("Pong actor") {
    it("should respond with Pong") {
      val future = pongActor ? "Ping" //uses the implicit timeout
      val result = Await.result(future.mapTo[String], Duration(1, TimeUnit.MICROSECONDS))
      assert(result == "Pong")
    }
    it("should fail on unknown message") {
      val future = pongActor ? "unknown"
      intercept[Exception] {
        Await.result(future.mapTo[String], Duration(1, TimeUnit.MICROSECONDS))
      }
    }
    describe("FutureExamples") {
      import scala.concurrent.ExecutionContext.Implicits.global
      it("should print to console") {
        (pongActor ? "Ping").onSuccess({
          case x: String => println("replied with: " + x)
        })
        Thread.sleep(100)
      }
    }
  }
}
