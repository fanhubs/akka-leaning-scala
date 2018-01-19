package com.lightbend.akka.sample.wordscount

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
/**
  * Created by qiang on 18-1-9.
  */
object DocumentExample extends App{

  val system = ActorSystem("System")
  val actor = system.actorOf(Props(new FileProcessActor()))
  implicit val timeout = Timeout(25 seconds)
  val future = actor ? FILE_PROCESS_MSG("/tmp/word-count")

  println(future.toString)

}
