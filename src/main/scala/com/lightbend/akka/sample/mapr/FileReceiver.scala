package com.lightbend.akka.sample.mapr

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.routing._

/**
  * Created by qiang on 18-1-14.
  */
class FileReceiver extends Actor with ActorLogging{

  def receive = {
    case fileName:String =>
      val system =ActorSystem("receiversystem")

      val globalActorRef = system.actorOf(Props(new CountAggregator(8)))

      val localAggregatorActorRef =
        system.actorOf(Props(new LocalAggregator(globalActorRef)))

      val lineCollector =
        system.actorOf(Props(new LineCollector(localAggregatorActorRef)))

      //val router =
      //  system.actorOf(Props(new LineCollector(system.actorOf(Props(new LocalAggregator(globalActorRef))))).withRouter(new RoundRobinRoutingLogic(nrOfInstances = 8)))

      //val globla = system.actorOf(Props[new LineCollector()])
      //val global = system.actorof(Props)
  }

}
