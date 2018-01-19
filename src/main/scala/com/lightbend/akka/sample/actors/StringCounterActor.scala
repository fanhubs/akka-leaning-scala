package com.lightbend.akka.sample.actors

import akka.actor.{Actor, ActorLogging, actorRef2Scala}


case class StartProcessFileMsg()

//case class FILE_PROCESS_MSG()

case class ProcessLineMsg(string: String)

//case class LINE_PROCESS_MSG(line:String)

case class StringProcessedMsg(words: Integer)

//case class WORDS_PROCESS_MSG(words:Integer)

/**
  * Created by qiang on 18-1-9.
  */
class StringCounterActor extends Actor with ActorLogging{

  def receive = {
    case ProcessLineMsg(string) => {
      log.info("I got your message "+string)
      val wordsInLine = string.split(" ").length
      sender ! StringProcessedMsg(wordsInLine)
    }
    case _ => log.info("Error: message not recognized")
  }

}
