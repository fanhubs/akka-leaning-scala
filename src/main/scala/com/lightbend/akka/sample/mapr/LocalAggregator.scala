package com.lightbend.akka.sample.mapr
import java.io.{File, RandomAccessFile}
import java.nio.channels.FileChannel
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem}

/**
  * Created by qiang on 18-1-15.
  */
class LocalAggregator (globalAgg:ActorRef) extends Actor with ActorLogging{

  val wordCountMap = scala.collection.mutable.Map[String,Int]()

  def receive = {
    //case countMap :scala.collection.mutable.Map[String, List[String]] =>
    //  countMap map { case (k, v) => wordCountMap += ((k, wordCountMap.getOrElse(k, 0) + v.size)) }

    case complete:Boolean => globalAgg ! wordCountMap
  }
}
