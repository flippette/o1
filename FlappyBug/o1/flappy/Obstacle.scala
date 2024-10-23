package o1.flappy

import o1.*

import scala.util.Random

// This class is introduced in Chapter 2.6.

class Obstacle(val radius: Int) extends HasVelocity:
  private var currentPos = this.randomLaunchPosition()
  def pos = this.currentPos

  private def randomLaunchPosition() =
    Pos(ViewWidth + this.radius + Random.between(0, 500), Random.between(0, ViewHeight))

  def velocity: Velocity = Velocity(-ObstacleSpeed, 0)

  def approach() =
    this.currentPos = if isActive then this.nextPos else this.randomLaunchPosition()

  override def toString = "center at " + this.pos + ", radius " + this.radius

  def touches(bug: Bug) = this.pos.distance(bug.pos) <= this.radius + bug.radius

  def isActive = this.pos.x + this.radius >= 0

end Obstacle
