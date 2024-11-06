package o1.robots

import o1.*

class Slaybot(name: String, body: RobotBody) extends RobotBrain(name, body):
  def moveBody(): Unit =
    CompassDir.Clockwise
      .map(dir => (dir, this.location.pathTowards(dir)))
      .flatMap((dir, path) => path.find(this.world(_).nonEmpty).map((dir, _)))
      .find((dir, pos) => this.world(pos).robot.exists(_.isIntact)) match
      case Some((dir, pos)) =>
        this.body.spinTowards(dir)
        this.world(pos).robot.foreach(_.destroy())
        while this.body.moveTowards(dir) do ()
      case _ => ()
end Slaybot
