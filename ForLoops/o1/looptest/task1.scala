package o1.looptest

// This program is associated with Chapter 5.5.

@main def forTask1() =

  val numbers = Vector(3.4, 6.5, 2.3, 3, 1.2, 5.41, 5.1, 9.1, 3.4, 7.8, 10, 9.1, 5, 2.1, 1.2)  // Leave this line as it is.

  println("The Beginning.")
  numbers.foreach(n => println(s"-----\n${n * 2}"))
  println("The End.")

end forTask1

