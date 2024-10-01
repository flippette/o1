package o1.flappy

import o1.*

class Game:
  val bug = Bug(Pos(100.0, 40.0))
  val obstacle = Obstacle(70)

  def timePasses() =
    this.bug.fall()
    this.obstacle.approach()

  def activateBug() =
    this.bug.flap(15.0)

  def isLost = this.obstacle.touches(this.bug)
end Game