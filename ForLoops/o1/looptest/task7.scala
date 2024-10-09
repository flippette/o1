package o1.looptest

// This file is associated with Chapter 5.6.

import scala.math.*
import scala.io.StdIn.*

@main def forTask7() =

  val upperLimit = readLine("Enter an upper limit for the two legs of a right triangle: ").toInt // Do not edit this line.

  // The example below illustrates the expected printout when upperLimit is 3.
  // Below these comments, write a program that prints out lists such as the
  // one shown here.
  //
  // 1 and 1 -----> 1.4142135623730951
  // 1 and 2 -----> 2.23606797749979
  // 1 and 3 -----> 3.1622776601683795
  // 2 and 2 -----> 2.8284271247461903
  // 2 and 3 -----> 3.605551275463989
  // 3 and 3 -----> 4.242640687119285
  //
  //
  // Elaboration:
  //
  // Each line of output corresponds to a right triangle: the decimal number on
  // the right is the length of a hypotenuse, and the two integers on the left
  // are the legs (i.e., the other two sides). The program considers each possible
  // combination of legs whose lengths are integers between 1 and upperLimit.
  //
  // The same combination of legs must not appear twice. For example, the above
  // output does not list both "1 and 3" and "3 and 1".
  //
  // Use nested for loops. That is, place one for loop inside another. Do not
  // add any code outside the loops.
  //
  // Please use the "to" method, not "until", to construct the numerical ranges
  // that you loop over. (In principle, "until" would work, too, but it’s not
  // any better, and our auto-grader would prefer that you use "to", so there.)
  //
  // Hint:
  // You can use the following line of code as part of your program.
  // Place this within the two nested loops:
  // println(s"$first and $second -----> ${hypot(first, second)}")
  //
  // Write your code below.

  for a <- 1 to upperLimit do
    for b <- a to upperLimit do
      println(s"$a and $b -----> ${sqrt(a * a + b * b)}")

end forTask7

