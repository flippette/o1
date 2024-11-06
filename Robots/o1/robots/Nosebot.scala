package o1.robots

import o1.*

class Nosebot(name: String, body: RobotBody) extends RobotBrain(name, body):
  override def mayMove(direction: CompassDir): Boolean =
    this.world.elementAt(this.location.neighbor(direction)).isEmpty

  def moveBody(): Unit =
    val currentDir = this.facing
    while !this.attemptMove() && this.facing != currentDir do ()

  def attemptMove(): Boolean =
    val moved = this.advanceCarefully()
    if !moved then this.body.spinClockwise()
    moved
end Nosebot