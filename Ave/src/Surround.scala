import scala.io.StdIn.*

@main def surround() =
  // the assessment is broken :shrug:
  println("Please enter a string: ")
  val mid = readLine()
  println("Add a surrounding string: ")
  val sur = readLine()
  println(s"$sur$mid$sur")