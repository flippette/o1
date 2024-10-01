package o1.subprograms

import o1.*

import scala.swing.Image

// WRITE YOUR OWN FUNCTIONS BELOW. (You may remove the example function.)

def twoByTwo(img: Pic): Pic =
  val row = img.leftOf(img)
  row.above(row)

def flagOfSomalia(width: Double): Pic =
  star(width * 4.0 / 13.0, White).onto(rectangle(width, width * 2.0 / 3.0, RoyalBlue))

def flagOfFinland(width: Double): Pic =
  val x = width / 18.0
  val topRow = rectangle(5 * x, 4 * x, White).leftOf(rectangle(3 * x, 4 * x, Blue)).leftOf(rectangle(10 * x, 4 * x, White))
  val middleRow = rectangle(5 * x, 3 * x, Blue).leftOf(rectangle(3 * x, 3 * x, Blue)).leftOf(rectangle(10 * x, 3 * x, Blue))
  topRow
    .above(middleRow)
    .above(topRow)

def clownify(original: Pic, pos: Pos): Pic = original.place(circle(15, Red), pos)

def leftSide(pic: Pic, amount: Double): Pic = pic.crop(Pos(0.0, 0.0), pic.width * amount / 100.0, pic.height)
def rightSide(pic: Pic, amount: Double): Pic = pic.crop(Pos(pic.width - pic.width * amount / 100.0, 0.0), pic.width * amount / 100.0, pic.height)
def foldIn(pic: Pic, edgeAmount: Double): Pic = leftSide(pic, edgeAmount).leftOf(rightSide(pic, edgeAmount))

def flagOfCzechia(width: Double): Pic =
  val height = width * 2.0 / 3.0
  val tri = triangle(height, width / 2.0, MidnightBlue).clockwise(90)
  val top = rectangle(width, height / 2.0, White)
  val bot = rectangle(width, height / 2.0, Crimson)
  top.onto(bot, BottomCenter, TopCenter).place(tri, CenterRight, Center)