package o1.legal

trait Restriction(val description: String):
  override def toString = s"capacity reduced due to $description"

object Illness extends Restriction("mental and/or physical condition")
object Underage extends Restriction("age")