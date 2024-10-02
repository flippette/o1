package o1.football3

import scala.collection.mutable.Buffer
import scala.math.abs

class Season:
  private val matches: Buffer[Match] = Buffer.empty;

  def addResult(newResult: Match): Unit = this.matches += newResult
  def biggestWin: Option[Match] = this.matches.maxByOption(_.goalDifference.abs)
  def latestMatch: Option[Match] = this.matches.lastOption
  def matchNumber(number: Int): Option[Match] = this.matches.lift(number)
  def numberOfMatches: Int = this.matches.size
end Season