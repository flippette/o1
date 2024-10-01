package o1.space
import o1.*

// This code is introduced in Chapter 2.7.

val space = AreaInSpace(500)

val emptySpacePic = rectangle(space.width, space.height, Black)
val earthPic = circle(space.earth.radius * 2, MediumBlue)
val moonPic  = circle(space.moon.radius  * 2, Beige)

// Replace the question marks below with code that works.
object window extends View("A Very Simple View of Space"):

  def makePic = ???

end window

@main def launchSpaceProgram() =
  ???  // Should launch the view that the name "window" refers to.

