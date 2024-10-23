package o1.rainfall
import scala.io.StdIn.*

object RainfallApp extends App:
  val data = LazyList.continually(readLine("Enter rainfall (or 999999 to stop): "))
    .flatMap(_.toIntOption)
    .takeWhile(_ != 999999)
    .toVector
  averageRainfall(data) match {
    case Some(avg) => println(s"The average is $avg")
    case _ => println("No valid data. Cannot compute the average.")
  }
end RainfallApp