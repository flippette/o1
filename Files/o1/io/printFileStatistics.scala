package o1.io

import scala.io.Source
import scala.io.StdIn.*
import scala.math.Ordering.Double.TotalOrdering

// This program is introduced in Chapter 11.3.

@main def printFileStatistics() =

  val fileName = readLine("Please enter the name of the input file: ")

  val moduleFolderName = "Files/"

  val file = Source.fromFile(moduleFolderName + fileName)

  try
    val nums = file.getLines().map(_.toDoubleOption).takeWhile(_.isDefined).flatten.toVector
    println(s"Count: ${nums.length}")
    println(s"Average: ${average(nums)}")
    println(s"Median: ${median(nums)}")
    println(s"Highest number of occurrences: ${maxOccurrences(nums)}")
  finally
    file.close()

end printFileStatistics


def average(numbers: Vector[Double]) = numbers.sum / numbers.size

def median(numbers: Vector[Double]) =
  val halfway = numbers.size / 2
  val ascending = numbers.sorted
  if numbers.size % 2 == 1 then
    ascending(halfway)
  else
    (ascending(halfway - 1) + ascending(halfway)) / 2

def maxOccurrences(numbers: Vector[Double]) = numbers.groupBy(identity).values.map(_.size).max

