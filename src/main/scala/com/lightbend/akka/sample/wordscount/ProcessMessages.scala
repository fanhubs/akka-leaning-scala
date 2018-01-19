package com.lightbend.akka.sample.wordscount

case class FILE_PROCESS_MSG(filePath:String)

//case class ProcessLineMsg(string: String)

case class LINE_PROCESS_MSG(line:String)

//case class StringProcessedMsg(words: Integer)

case class WORDS_PROCESS_MSG(word:String)
