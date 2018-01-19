package com.lightbend.akka.sample.mapr

import akka.actor.{Actor, ActorLogging}

/**
  * Created by qiang on 18-1-15.
  */
class CountAggregator(threadCout:Int) extends Actor with ActorLogging{

  val wordCoutMap = scala.collection.mutable.Map[String,Int]()
  var count:Integer=0

  def receive={
    case localCout:scala.collection.mutable.Map[String,Int]=>
      localCout.map(x=>wordCoutMap += ((x._1, wordCoutMap.getOrElse(x._1,0)+x._2)))
      count = count+1
      if(count==threadCout){
        println("Got the completion message, ook")
      }
  }

  def onCompletion={
    for (word<- wordCoutMap.keys){
      print(word +" =>")
      println(wordCoutMap.get(word).get)
    }

    print(s"Completed at =>")
    println(System.currentTimeMillis())
  }

}
