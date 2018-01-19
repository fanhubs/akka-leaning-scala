package com.lightbend.akka.sample.greatings

import akka.actor.{Actor, ActorLogging, ActorRef, Props, UntypedAbstractActor}



/**
  * Created by qiang on 18-1-19.
  */
class HelloWorld extends Actor with ActorLogging{


  override def preStart() = {

    log.debug("Okay ladays gentelmen, let's get start work")
    val greeter = context.actorOf(Props( new Greeter),"greeter")
    log.debug("step #1, we have started work on the initial greeter")
    greeter ! Greet_Message.GREET

    log.debug("step #2, the message - GREET was sent out")

  }


  def receive = {
    case Greet_Message.DONE => {
      log.debug("setp #6, we got the message that the job was done.")
      context.stop(self)
      log.debug("setp #7, we got the message that the job was done.")
    }

    case _ => log.debug("this call from Hello world, we have unidentified message.")
  }

}
