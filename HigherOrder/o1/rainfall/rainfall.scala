package o1.rainfall

// What goes here is described in Chapter 7.1.

def averageRainfall(data: Vector[Int]): Option[Int] =
  val series = data.takeWhile(_ != 999999).filter(_ >= 0)
  series.length match
    case 0 => None
    case other => Some(series.sum / other)

def averageRainfallFromStrings(data: Vector[String]): Option[Int] =
  val series = data.flatMap(_.toIntOption).takeWhile(_ != 999999).filter(_ >= 0)
  series.length match
    case 0 => None
    case other => Some(series.sum / other)

def drySpell(data: Vector[Int], length: Int): Int =
  data.sliding(length).indexWhere(_.forall(0 to 5 contains _))