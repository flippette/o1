package o1.trotter
import o1.*

// This program is introduced in Chapter 3.6.

/** A simple app that lets the user control a “trotter” onscreen. */
@main def runTrotterApp() =
  trotterView.start()

val game = TrotterGame(20, 40)

object trotterView extends View("Trotter App", 8):

  private val background =
    val gray = rectangle(game.horseStep, game.horseStep, Gray)
    val blue = rectangle(game.horseStep, game.horseStep, LightBlue)
    val row1 = gray.alternatingRow(blue, game.sizeInSteps)
    val row2 = blue.alternatingRow(gray, game.sizeInSteps)
    row1.alternatingColumn(row2, game.sizeInSteps)

  private val horsePic =
    Pic("horse.png").scaleTo(game.horseStep * 1.5) // a bit bigger than the square looks nice

  def makePic = background.place(horsePic, game.horsePos)

  override def onTick() =
    game.advance()

  override def onKeyDown(key: Key) =
    Direction.fromArrowKey(key) match
      case Some(dir) => game.horseHeading = dir
      case _ => ()

end trotterView

