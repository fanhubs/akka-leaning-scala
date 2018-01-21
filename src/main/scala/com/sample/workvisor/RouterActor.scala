package com.sample.workvisor

import java.util

import akka.actor.{Actor, ActorLogging, Props, Terminated}
import akka.routing._
import com.sample.workvisor.WORK_MESSAGE.WORK_MESSAGE

/**
  * Created by qiang on 18-1-21.
  */
class RouterActor extends Actor with ActorLogging{

  def router:Router={
    val routees =new  util.ArrayList[Routee]()
    for(i<-1 to 5){
      val workerRef = context.actorOf(Props(new BoxActor),"BoxActor")
      context.watch(workerRef)
      routees.add(new ActorRefRoutee(workerRef))
    }

    /**
      * RoundRobinRoutingLogic: 轮询
      * BroadcastRoutingLogic: 广播
      * RandomRoutingLogic: 随机
      * SmallestMailboxRoutingLogic: 空闲
      */
    new Router(new RoundRobinRoutingLogic,routees)

  }

  def receive={

    case singnal:Object =>{

      //for the work message
      if(singnal.isInstanceOf[WORK_MESSAGE])
        router.route(singnal,sender())

      //for the close commands
      if(singnal.isInstanceOf[Terminated]){
        //router.routees = router.removeRoutee(singnal.asInstanceOf[Terminated].actor).routees


      }


    }
  }

}
