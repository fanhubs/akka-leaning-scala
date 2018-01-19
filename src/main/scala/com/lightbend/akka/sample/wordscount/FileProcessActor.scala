package com.lightbend.akka.sample.wordscount

import akka.actor.{Actor, ActorLogging, Props, actorRef2Scala}

/**
  * Created by qiang on 18-1-10.
  */
class FileProcessActor extends Actor with ActorLogging {



  /**
    * let's start working and get job done.
    * author - Qiang
    * version - 2018 01 10
    */
  def receive = {

    /**
      * the file process will be working on the
      * file which was assigned to program..
      *
       */
    case FILE_PROCESS_MSG(filePath:String) => {

      import scala.io.Source._

      fromFile(filePath).getLines().foreach {
        line => {
          log.debug("the line message is ->"+line)
          context.actorOf(Props[LineProcessActor]) ! LINE_PROCESS_MSG(line)}

      }
    }

    case _ =>{

      println("//=======THIS MSG WAS SENT FROM File_PROCESS_ACTOR====")
      println("//=======THIS Message means that you sent an incorrect message to File Process Actor===")
    }
  }
}
