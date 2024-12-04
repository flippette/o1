package o1.io

import scala.io.Source

// This program is introduced in Chapter 11.3.

@main def readingExample1() =

  val file = Source.fromFile("Files/example.txt")

  println("The first  character in the file is " + file.next() + ".")
  println("The second character in the file is " + file.next() + ".")
  println("The third  character in the file is " + file.next() + ".")
  println("The fourth character in the file is " + file.next() + ".")
  println("The fifth  character in the file is " + file.next() + ".")
  println("The sixth  character in the file is " + file.next() + ".")

  file.close()

end readingExample1

