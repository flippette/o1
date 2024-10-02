package o1.misc
import o1.*

def isPortrait(pic: Pic): Boolean = pic.width < pic.height
def describe(pic: Pic): String = if isPortrait(pic) then "portrait" else if pic.width == pic.height then "square" else "landscape"
def together(voices: Vector[String], tempo: Int) = s"${voices.mkString("&")}/$tempo"