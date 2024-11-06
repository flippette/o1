package o1.robots

import o1.*

class Lovebot(name: String, body: RobotBody, val beloved: RobotBody) extends RobotBrain(name, body):
  def moveBody() =
    if this.body.location.distance(this.beloved.location) > 1 then
      this.body.location.diff(this.beloved.location) match
        case (a, b) if a.abs >= b.abs && a > 0 => this.body.moveTowards(East)
        case (a, b) if a.abs >= b.abs => this.body.moveTowards(West)
        case (_, b) if b > 0 => this.body.moveTowards(South)
        case _ => this.body.moveTowards(North)
end Lovebot



