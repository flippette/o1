package o1.flappy

import o1.*

class Bug(private var currentPos: Pos):
  val radius: Int = BugRadius
  private var yVelocity = 0.0

  def pos = this.currentPos

  override def toString: String = s"center at ${this.pos}, radius ${this.radius}"

  def flap(strength: Double) = this.yVelocity = -strength
  def fall() =
    if this.currentPos.y < ViewHeight - GroundDepth then this.yVelocity = this.yVelocity + FallingSpeed
    move(this.yVelocity)

  private def move(down: Double) =
    this.currentPos = this.currentPos.addY(down).clampY(0, 350)

  def isInBounds: Boolean = this.currentPos.y > 0 && this.currentPos.y < ViewHeight - GroundDepth
end Bug
