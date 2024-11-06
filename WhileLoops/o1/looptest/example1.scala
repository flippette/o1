package o1.looptest

// This program is introduced in Chapter 9.1.

import scala.io.StdIn.*

@main def whileExample1() =

  var name = ""

  while name.isEmpty do
    name = readLine("Enter your name (at least one character, please): ")
    val len = name.length
    println(s"The name is $len character${if name.length != 1 then "s" else ""} long.")

  println(s"OK. Your name is $name. Hello!")

end whileExample1

