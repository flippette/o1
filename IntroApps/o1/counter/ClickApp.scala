package o1.counter
import o1.*

// This program is introduced in Chapter 3.1.

val clickCounter = Counter(5)
val blueBackground = rectangle(500, 500, Blue)

object clickView extends View("Click Test"):

  def makePic =
    blueBackground.place(circle(clickCounter.value, White), Pos(100, 100))

  override def onClick(locationOfClick: Pos) =
    clickCounter.advance()
    println("Click detected at " + locationOfClick + "; " + clickCounter)

end clickView


@main def runClickProgram() =
  clickView.start()

