package o1.looptest

// This program is introduced in Chapter 9.1.

@main def whileExample2() =

  var number = 1
  println(number)

  while number < 10 do
    println(number)
    number += 4
    println(number)

  println(number)

end whileExample2

