package com.sample.workvisor

import akka.actor.{Actor, ActorLogging, Terminated,ActorRef}

/**
  * Created by qiang on 18-1-20.
  */
class WatchActor (actorRef:ActorRef) extends Actor with ActorLogging{

  log.debug("the WatchActor will be initiated")
  this.context.watch(actorRef)
  log.debug("the WatchActor was initiated")


  def receive () ={

    case (msg:Object) =>
    if(msg.isInstanceOf[Terminated]){
      log.error(msg.asInstanceOf[Terminated].actor.path+" has Terminated. now shutdown the system")
      context.system.terminate()
      log.debug("the system was terminated")
    }
    case WORK_MESSAGE.UNKONW=> log.info("as per you input, we found something.")

  }

}
