package o1.flappy

import o1.*

class Bug(private var currentPos: Pos) extends HasVelocity:
  val radius: Int = BugRadius
  private var yVelocity = 0.0

  def pos = this.currentPos

  def velocity: Velocity = Velocity(0, yVelocity)

  override def toString: String = s"center at ${this.pos}, radius ${this.radius}"

  def flap(strength: Double) = this.yVelocity = -strength

  def fall() =
    if this.currentPos.y < ViewHeight - GroundDepth then this.yVelocity = this.yVelocity + FallingSpeed
    this.currentPos = this.nextPos.clampY(0, 350)

  def isInBounds: Boolean = this.currentPos.y > 0 && this.currentPos.y < ViewHeight - GroundDepth
end Bug
