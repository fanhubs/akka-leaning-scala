package com.sample.workvisor

/**
  * Created by qiang on 18-1-20.
  */
object WORK_MESSAGE extends Enumeration{

  type WORK_MESSAGE=Value
  val WORKING, DONE, CLOSE, UNKONW = Value

}

case class WATCH_MESSAGE(msg:Object)
