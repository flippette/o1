package o1.glider
import o1.*

// This program is introduced in Chapter 3.6.

/** A simple app that lets the user control a “glider” onscreen. */
@main def runGliderApp() =
  gliderView.start()

val glider = Glider(Pos(350, 350), 25)

object gliderView extends View("Glider App"):

  private val background = square(700, LightBlue)
  private val gliderPic = Pic("glider.png").scaleTo(50)

  def makePic =
    val rotatedglider = gliderPic.counterclockwise(glider.heading.toDegrees)
    background.place(rotatedglider, glider.pos)

  override def onTick() =
    glider.glide()

  override def onKeyDown(key: Key) =
    if key == Key.Up then
      glider.isAccelerating = true
    if key == Key.Left then
      glider.isTurningLeft = true
    if key == Key.Right then
      glider.isTurningRight = true

  override def onKeyUp(key: Key) =
    if key == Key.Up then
      glider.isAccelerating = false
    if key == Key.Left then
      glider.isTurningLeft = false
    if key == Key.Right then
      glider.isTurningRight = false

end gliderView