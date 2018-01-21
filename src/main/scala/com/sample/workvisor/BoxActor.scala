package com.sample.workvisor

import akka.actor.{Actor, ActorLogging}

/**
  * Created by qiang on 18-1-21.
  */
class BoxActor extends Actor with ActorLogging{

  def receive={

    case WORK_MESSAGE.WORKING=>
      log.info("BoxActor-I got message that you will be working from right now.")

    case WORK_MESSAGE.DONE =>
      log.info("BoxActor-I am done for the work that you assigned to me.")

    case WORK_MESSAGE.CLOSE => {
      log.info("BoxActor -I will be closed")
      sender() ! WORK_MESSAGE.CLOSE
      context.stop(this.self)
    }

  }

}
