package o1.flappy

import o1.*

class Game:
  val bug = Bug(Pos(100.0, 40.0))
  val obstacles = Vector(Obstacle(70), Obstacle(30), Obstacle(20))

  def timePasses() =
    this.bug.fall()
    this.obstacles.foreach(_.approach())

  def activateBug() =
    this.bug.flap(15.0)

  def isLost =
    !this.bug.isInBounds
      || this.obstacles.exists(
        obs => bug.pos.distance(obs.pos) <= bug.radius + obs.radius
      )
end Game