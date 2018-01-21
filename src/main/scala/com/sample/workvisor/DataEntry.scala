package com.sample.workvisor

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Inbox, PoisonPill, Props, Terminated}
import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration

/**
  * Created by qiang on 18-1-20.
  */
object DataEntry extends App{

  val system = ActorSystem.create("HelloSystem")

  val mywork = system.actorOf(Props(new MyWork()),"MyworkActor")
  val watchWork = system.actorOf(Props(new WatchActor(mywork)),"WatchworkActor")
  val inboxActor = system.actorOf(Props(new BoxActor),"BoxActor")

  val inbox = Inbox.create(system)
  inbox.watch(inboxActor)


  inbox.send(inboxActor,WORK_MESSAGE.WORKING)
  inbox.send(inboxActor,WORK_MESSAGE.DONE)
  inbox.send(inboxActor,WORK_MESSAGE.CLOSE)


  while(true){
    val receive = inbox.receive(Duration.create(1,TimeUnit.SECONDS))

    if (receive.equals(WORK_MESSAGE.CLOSE))
      println("the inboxActor is closed")

    if (receive.isInstanceOf[Terminated]){
      println("the inboxActor is close")
      system.terminate()
    }



  }

  //mywork! WORK_MESSAGE.WORKING
  //mywork! WORK_MESSAGE.DONE
  //mywork! WORK_MESSAGE.UNKONW

  //mywork ! PoisonPill


}
