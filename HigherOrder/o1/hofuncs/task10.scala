package o1.hofuncs
import o1.*

// This assignment from Chapter 7.1 is completely optional.

def blur(pic: Pic): Pic =
  pic.transformXY((x, y) =>
    val neighbors = pic.pixelsNear(Pos(x, y), 1.415)
    val avg = neighbors
      .map(c => Vector(c.red, c.green, c.blue))
      .reduce(_.zip(_).map(_ + _))
      .map(_ / neighbors.length)
    Color(avg(0), avg(1), avg(2))
  )

// Implement the blur function above so that it takes in a Pic and returns a
// blurred version of it. The blurred version is formed by transforming each
// pixel in the given Pic as follows:
//
//    1) Find out the pixel’s “neighborhood”: the pixel itself and
//       those pixels that are adjacent to it. Diagonally adjacent
//       counts, so a pixel’s neighborhood will consist of up to nine
//       pixels in total. (The neighborhood of a border pixel is smaller.)
//
//    2) Compute the redness of the neighborhood: the average of those
//       pixels’ red components. Do the same for the green component and
//       the blue component.
//
//    3) That produced three values. Use them as the blurred pixel’s
//       red, green, and blue components, respectively.
//
//  When your function works, the following code will display a photo
//  side-by-side with a slightly blurred version.

@main def task10() =
  val originalPic = Pic("kid.png")
  val blurredPic = blur(originalPic)
  originalPic.leftOf(blurredPic).show()
