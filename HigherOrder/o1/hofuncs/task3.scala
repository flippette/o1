package o1.hofuncs
import o1.*

// This program is introduced in Chapter 6.1.

// This task is similar to the previous one: you create an image filter for
// unscrambling another hidden image. Here is what you need to know this time:
//
// * This image, too, contains some randomly generated noise.
//   The values of the red and green component are meaningless.
//
// * Some but not all values of the blue component are important:
//
//      * Values between 0 and 15 are meaningful. However,
//        they are rather too dim. Multiply them by sixteen
//        to obtain the appropriate degree of blueness.
//      * All other values (16–255) of the blue component are
//        meaningless and should be replaced by zero.
//
// * The hidden image is in grayscale, so every pixel’s R, G, and B components
//   are equal. Assign the amplified blueness to each component.

def processColor(color: Color): Color =
  val comp = color.blue match {
    case v if 0 to 15 contains v => v * 16
    case _ => 0
  }
  Color(comp, comp, comp)

@main def task3() =
  val scrambledPic = Pic("hidden2.png")
  val solvedPic = scrambledPic.transformColors(processColor) // TODO: should use transformColors to reveal the hidden image
  launchUnscramblerGUI(scrambledPic, solvedPic)


