package o1.io

import scala.io.Source
import o1.play

// This program is introduced in Chapter 11.3.

@main def readingExample4() =

  val file = Source.fromFile("Files/running_up_that_hill.txt")

  try 
    val entireContents = file.mkString
    play(entireContents)
  finally 
    file.close()

end readingExample4

