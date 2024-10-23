package o1.shapes

import scala.math.sqrt

class RightTriangle(val edgeA: Double, val edgeB: Double) extends Shape:
  def hypotenuse: Double = sqrt(this.edgeA * this.edgeA + this.edgeB * this.edgeB)
  def area: Double = this.edgeA * this.edgeB / 2.0
  def perimeter: Double = this.edgeA + this.edgeB + this.hypotenuse
end RightTriangle