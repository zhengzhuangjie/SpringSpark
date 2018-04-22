package com.example.demo

import java.util

import akka.actor.{AbstractActor, Actor, ActorRef, ActorSelection, ActorSystem, Props}
import akka.event.Logging

case class SetRequest(key: String, value: Object)

class AkkademyDb extends Actor {
  val map = new util.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive(): Receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key:() value:()", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: ()", o)
  }
}

object AkkademyDb {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("AkkademyDb")
    val actor: ActorRef = actorSystem.actorOf(Props(classOf[AkkademyDb]))
    println(actor.path)
    actor ! SetRequest("hello", "world")
    val selection: ActorSelection = actorSystem.actorSelection(actor.path)

  }

}