package com.lightbend.akka.sample.wordscount

import akka.actor.{Actor, ActorLogging, Props, actorRef2Scala}

/**
  * Created by qiang on 18-1-10.
  */
class LineProcessActor extends Actor with ActorLogging{

  def receive ={
    // start line process
    case LINE_PROCESS_MSG (line:String) => {

      println("===== The LineProcessActor is working now")
      val words = line.split(" ").toList
      words.foreach{
        word=> context.actorOf(Props[WordsProcessActor]) ! WORDS_PROCESS_MSG(word) }
    }
    // check if incorrect message was sent
    case _ =>{
      println("//=======THIS MSG WAS SENT FROM LINE_PROCESS_ACTOR")
      println("//=======This message means that you sent an inorrect message to Line Process Actor")
    }
  }

}
