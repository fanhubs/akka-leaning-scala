package com.sample.greatings

import akka.actor.{ActorSystem, Props}

/**
  * Created by qiang on 18-1-19.
  */
object DataEntry extends App{

  val system = ActorSystem.create("Hello")
  val helloWorld = system.actorOf(Props(new HelloWorld))
  println(helloWorld.path)

}
