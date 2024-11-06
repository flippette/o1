package o1.looptest

// This task is part of Chapter 9.1.

import scala.io.StdIn.*

// Example run (with user inputs in *asterisks*:
//
// I will compute the squares of positive integers and discard other numbers.
// To stop, just hit Enter.
// Please enter the first number: *10*
// Its square is: 100
// Another number: *0*
// Another number: *-1*
// Another number: *20*
// Its square is: 400
// Another number: *30*
// Its square is: 900
// Another number: *0*
// Another number: *40*
// Its square is: 1600
// Another number:
// Done.
// Number of discarded inputs: 3

@main def whileTask2(): Unit =
  println("I will compute the squares of positive integers and discard other numbers.")
  println("To stop, just hit Enter.")
  var discarded = 0

  def feedback(str: String): Option[Option[String]] =
    str.toIntOption.map {
      case i if i <= 0 => None
      case i => Some(s"Its square is: ${i * i}")
    }
  end feedback

  var abort = false

  feedback(readLine("Please enter the first number: ")) match
    case None => abort = true
    case Some(None) => discarded += 1
    case Some(Some(feedback)) => println(feedback)

  while !abort do
    feedback(readLine("Another number: ")) match
      case None => abort = true
      case Some(None) => discarded += 1
      case Some(Some(feedback)) => println(feedback)

  println("Done.")
  println(s"Number of discarded inputs: $discarded")
end whileTask2