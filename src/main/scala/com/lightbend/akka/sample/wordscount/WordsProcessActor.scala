package com.lightbend.akka.sample.wordscount

import akka.actor.{Actor, ActorLogging}

/**
  * Created by qiang on 18-1-10.
  */
class WordsProcessActor extends Actor with ActorLogging{

  def receive={
    //Getting start on words
    case WORDS_PROCESS_MSG(word:String) =>{
      println(word)

    }

    case _ =>{

      log.debug("//=======THIS MSG WAS SENT FROM WORD_PROCESS_ACTOR====")
      log.debug("//=======THIS Message means that you sent an incorrect message to Word Process Actor===")
    }
  }

}
