package o1.looptest

import scala.io.StdIn.*

// This program is associated with Chapter 5.6.

@main def forTask5() =

  val inputText = readLine("Please enter a positive integer: ")

  // Below this comment, add a for loop that prints out the squares of all
  // positive integers, each on its own line, until it reaches the upper limit
  // indicated by the user. For instance, if the user enters the number 3,
  // the program should print:
  // 1
  // 4
  // 9
  //
  // Hint: Use toInt (Chapter 5.2) to interpret the input text as a number.
  // You do not have to account for invalid inputs such as "llama", "-1", or "10.5".
  1.to(inputText.toInt).foreach(n => println(s"${n * n}"))



end forTask5

