package com.sample.workvisor

import com.sample.workvisor.WORK_MESSAGE
import akka.actor.{Actor, ActorLogging}

/**
  * Created by qiang on 18-1-20.
  */
class MyWork extends Actor with ActorLogging{

  override def preStart ={
    log.info("#1, my work is starting")
  }

  override def postStop={
    log.info("#2, my work is stopping")
  }


  def receive={

    case WORK_MESSAGE.WORKING =>
      log.debug("I am working")

    case WORK_MESSAGE.DONE=>
      log.debug("done")
    case WORK_MESSAGE.CLOSE =>
      log.debug("close")

    case _ => log.debug("unknow message")



  }

}
