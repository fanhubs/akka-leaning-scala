package com.lightbend.akka.sample.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props, actorRef2Scala}

/**
  * Created by qiang on 18-1-9.
  */
class WordCounterActor (filename: String) extends Actor with ActorLogging {

  private var running = false
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = ""
  private var fileSender: Option[ActorRef] = None

  def receive = {
    case StartProcessFileMsg() => {
      log.info(" *** we get input file from - "+ filename)
      if (running) {
        log.info("*** Warning: duplicate start message received")

      } else {
        log.info(" *** we are ready to start working on this file - "+filename)
        running = true
        fileSender = Some(sender)
        import scala.io.Source._
        fromFile(filename).getLines().foreach {
          line => context.actorOf(Props[StringCounterActor]) ! ProcessLineMsg(line)
        }
      }
    }

    case ProcessLineMsg(words) => {
      result += words
      linesProcessed += 1
      if (linesProcessed == totalLines) {
        fileSender.map(_ ! result) // provide result to process invoker
      }
    }

    case _ => log.info("I am in the WordCounterActor, message not recognized!")

  }

}
