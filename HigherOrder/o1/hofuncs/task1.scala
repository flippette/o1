package o1.hofuncs
import o1.*

// This program is introduced in Chapter 6.1.

def toNaiveGray(color: Color) =
  val luminance = (color.red + color.blue + color.green) / 3
  Color(luminance, luminance, luminance)

// The function toNaiveGray above takes in a color and returns a grayscale (“colorless”)
// version of it, that is, a shade of gray between white and black. As you can see from
// the implementation, the R, G, and B components of a shade of gray are equal.
//
// toNaiveGray computes the shade of gray with a simple average: each of the three
// components has an equal weight of 1/3. This works fairly okay, as you can tell by
// running the program.
//
// However, the human eye is most sensitive to green light and much less sensitive to blue.
// Therefore, to produce a grayscale image that humans perceive as equally luminous (bright)
// as the original, we need to give different weights to the components of the original color:
// the green component should have the greatest weight and the blue component the least.
// Here is a commonly used formula (see https://en.wikipedia.org/wiki/Grayscale):
//   luminance = 0.2126*R + 0.7152*G + 0.0722*B
//
// Such a grayscale filter that seeks to preserve perceived luminosity is called
// “colorimetric”.
//
// Your task is to modify this program by adding a function toColorimetricGray, which works
// like toNaiveGray except that it uses the above formula to return a more realistic shade
// of gray. Use the new function below (instead of toNaiveGray) to compute grayPic.
//
// Note: You can pass in Doubles as you construct a Color. They will be rounded to the
// nearest Int.
def toColorimetricGray(color: Color): Color =
  val luminance = color.red * 0.2126 + color.green * 0.7152 + color.blue * 0.0722
  Color(luminance, luminance, luminance)

@main def task1() =
  val originalPic = Pic("kid.png")
  val grayPic = originalPic.transformColors(toColorimetricGray)
  originalPic.leftOf(grayPic).show()

