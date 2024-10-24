package o1.glider
import o1.*
import scala.math.*

// This program is introduced in Chapter 3.6.

/** A “glider” is an entity that moves about on a two-dimensional plane.
  * It can accelerate and turn right or left. It has no breaks but is slowed
  * down by friction.
  *
  * @param pos       the position of the glider
  * @param maxSpeed  a limit beyond which the glider cannot accelerate
  */
class Glider(var pos: Pos, val maxSpeed: Double):

  private var velocity = Velocity(Direction.Right, 0)

  /** `true` whenever the glider’s acceleration is currently on; initially `false` */
  var isAccelerating = false
  /** `true` whenever the glider is being guided leftwards; initially `false` */
  var isTurningLeft  = false
  /** `true` whenever the glider is being guided leftwards; initially `false` */
  var isTurningRight = false

  /** The direction the glider’s front is facing. A glider can only move in this direction. */
  def heading = this.velocity.direction

  /** Does the following, in order:
    *
    * 1. Increases the glider’s speed by one (but no higher than its `maxSpeed`) if the
    *    glider is currently accelerating. Turns the glider 10 degrees left or right if
    *    it is currently being instructed to do so. (If instructed to turn both ways at
    *    the same time, the glider does both, so there’s effectively no change.)
    * 2. Moves the glider within its coordinate system. The glider’s position changes
    *    a number of units equal to the glider’s (new) speed, in a straight line towards
    *    the glider’s (new) heading.
    * 3. Finally, reduces the glider’s speed by 0.2 due to friction.
    */
  def glide() =
    this.adjustVelocity()
    this.pos = this.pos.nextPos(this.velocity)
    this.applyFriction()

  private def adjustVelocity() =
    if this.isAccelerating then this.velocity = this.velocity.faster(1).noFasterThan(this.maxSpeed)
    if this.isTurningLeft then this.velocity = this.velocity.counterclockwise(10)
    if this.isTurningRight then this.velocity = this.velocity.clockwise(10)

  private def applyFriction() =
    this.velocity = this.velocity.slower(0.2)

end Glider

