package o1.looptest

// This task is part of Chapter 9.1.

import scala.io.StdIn.*

@main def whileTask1() =
  def report(input: String) = "The input is " + input.length + " characters long."

  var inputs = readLine("Enter some text: ")
  while inputs != "please" do
    println(report(inputs))
    inputs = readLine("Enter some text: ")
  end while