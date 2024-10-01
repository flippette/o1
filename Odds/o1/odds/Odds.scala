package o1.odds

import scala.util.Random

// This class is developed gradually between Chapters 2.4 and 3.4.

class Odds(val wont: Int, val will: Int):

  def probability = this.will.toDouble / (this.wont + this.will)

  def fractional: String = s"${this.wont}/${this.will}"
  def decimal: Double = 1.0 / this.probability
  def winnings(bet: Double): Double = bet * this.decimal
  def moneyline: Int = if probability > 0.5 then -100 * this.will / this.wont else 100 * this.wont / this.will
  def not: Odds = Odds(this.will, this.wont)

  override def toString: String = fractional

  def both(other: Odds): Odds = Odds(this.wont * other.wont + this.will * other.wont + this.wont * other.will, this.will * other.will)
  def either(other: Odds): Odds = Odds(this.wont * other.wont, this.will * other.will + this.wont * other.will + this.will * other.wont)

  def isLikely = this.wont < this.will
  def isLikelierThan(other: Odds) = this.probability > other.probability

  def eventHappens() = Random.between(0, this.wont + this.will) < this.will
end Odds
