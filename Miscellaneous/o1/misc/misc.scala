package o1.misc
import o1.*

def isPortrait(pic: Pic): Boolean = pic.width < pic.height
def describe(pic: Pic): String = if isPortrait(pic) then "portrait" else if pic.width == pic.height then "square" else "landscape"
def together(voices: Vector[String], tempo: Int) = s"${voices.mkString("&")}/$tempo"
def insert(from: String, to: String, index: Int): String = s"${to.take(index)}$from${to.drop(index)}"
def tempo(melody: String): Int = melody.drop(melody.indexOf('/') + 1).toIntOption.getOrElse(120)
def toMinsAndSecs(secs: Int): (Int, Int) = (secs / 60, secs % 60)
def isAscending(vec: Vector[Int]): Boolean = vec.sliding(2).forall(v => v(0) <= v(1))