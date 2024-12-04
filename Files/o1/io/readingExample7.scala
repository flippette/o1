package o1.io

import scala.io.Source

// This program is introduced in Chapter 11.3.

@main def readingExample7() =

  val file = Source.fromFile("Files/example.txt")

  try
    val vectorOfLines = file.getLines.toVector

    println("LINES OF TEXT:")
    var lineNumber = 1              // stepper: 1, 2, 3, ...
    for line <- vectorOfLines do
      println(s"$lineNumber: $line")
      lineNumber += 1

    println("LENGTHS OF EACH LINE:")
    for line <- vectorOfLines do
      println(s"${line.length} characters")
    println("THE END")

  finally
    file.close()

  end try

end readingExample7

