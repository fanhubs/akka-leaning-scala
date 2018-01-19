package com.lightbend.akka.sample.mapr

import java.io.{File, RandomAccessFile}
import java.nio.channels.FileChannel

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem}

/**
  * Akka actor to receive file information and open file channel
  * to read and further distribute lines as chunks message.(Local mapper)
  *
  */
class LineCollector (localAgg:ActorRef) extends Actor with ActorLogging{

  def receive ={

    case (fileName:String, chunkStart:Int, chunkSize:Int) =>

      val file = new File(fileName)
      val chanel = new RandomAccessFile(file,"r").getChannel
      //map complete file
      val mappedBuff = chanel.map(FileChannel.MapMode.READ_ONLY,0,file.length())
      //load if it's not loaded
      var endP = chunkSize
      if(endP>=file.length()){
        endP=file.length().toInt -1
      }

      if(chunkStart < file.length()){
        var start = mappedBuff.get(chunkStart)
        val startPostion = trail(chunkStart, mappedBuff, start,endP)
        var end = mappedBuff.get(endP)

        val endPosition = if (endP != (file.length() - 1))
          trail(chunkStart, mappedBuff, start,endP)
        else
          endP

        val stringBuilder = new StringBuilder (endPosition - startPostion)

        val size = endPosition - startPostion
        val byteArray = new Array[Byte](size)

        //prepare and send buffer to local combiner
        if(endPosition > startPostion){
          for(i <- startPostion to endPosition) {
            val character = mappedBuff.get(i).asInstanceOf[Char]
            if(character =='\n')
              stringBuilder.append(' ')
            else
              stringBuilder.append(character)

          }
          localAgg ! stringBuilder.toString().split(" ").groupBy(x=>x)
        }
      }
  }

  /**
    *
    * @param startP
    * @param charBuff
    * @param start
    * @param length
    */
  private def trail(startP:Int, charBuff:java.nio.MappedByteBuffer,
                    start:Byte, length:Int):Int ={

    var startChar = start.asInstanceOf[Char]
    val position  = startP
    var next = position

    //
    if(position <= length)
      while (!startChar.equals(' ') && position >0){
        startChar = charBuff.get(next).asInstanceOf[Char]
        next = next-1
      }

    if(position != next) next +1 else position
  }

}
