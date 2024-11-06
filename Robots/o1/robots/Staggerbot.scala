package o1.robots

import o1.CompassDir
import o1.CompassDir.*

import scala.util.Random

class Staggerbot(name: String, body: RobotBody, randomSeed: Int) extends RobotBrain(name, body):
  private val dirs = Array(North, East, South, West)
  private val rng = Random(randomSeed)

  def moveBody() =
    if this.body.moveTowards(this.nextDir()) then
      this.body.spinTowards(this.nextDir())

  private def nextDir(): CompassDir = this.dirs(this.rng.nextInt(this.dirs.length))
end Staggerbot