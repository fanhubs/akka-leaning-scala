package com.lightbend.akka.sample.greatings

import akka.actor.{Actor, ActorLogging}


object Greet_Message extends Enumeration{
  type Greet_Message = Value
  val GREET, DONE = Value
}


/**
  * Created by qiang on 18-1-19.
  */
class Greeter extends Actor with ActorLogging{


  def receive = {

    case Greet_Message.GREET =>{
      log.debug("step #3, we have got the gree message from hello object")
      Thread.sleep(1000)
      log.debug("step #4, we sleped 1000 secondsk and ready to provide feedback")
      sender() ! Greet_Message.DONE
      log.debug("step #5, the feedback was sent out to sender..")
    }
    case _ => log.debug("It seems that we got incorrect messages")

  }

}
