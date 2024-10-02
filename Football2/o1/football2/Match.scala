package o1.football2

import scala.collection.mutable.Buffer
import scala.math.{max, min}

/** The class `Match` represents match results in a football match statistics program.
  * A match is played between teams from two clubs: a home club and an away club.
  * Goals scored by players of either team can be added to the match object with the
  * method `addGoal`.
  *
  * The class is expected to be used so that a match object with no goals is initially
  * created as a real-life match starts. Goals are added incrementally as the match
  * progresses. (A match object has mutable state.)
  *
  * @param home  the club whose team plays at home in the match
  * @param away  the club whose team plays away in the match */
class Match(val home: Club, val away: Club):
  private val homeScorers = Buffer[Player]()    // container: goalscorers of the home team are added here
  private val awayScorers = Buffer[Player]()    // container: goalscorers of the away team are added here
  private var homeGoalsNo = 0
  private var awayGoalsNo = 0

  def addGoal(scorer: Player): Unit =
    if scorer.employer == this.home then
      this.homeGoalsNo += 1
      this.homeScorers += scorer
    else
      this.awayGoalsNo += 1
      this.awayScorers += scorer

  def allScorers: Vector[Player] = (this.homeScorers ++ this.awayScorers).toVector

  def awayGoals: Int = this.awayGoalsNo

  def goalDifference: Int = this.homeGoals - this.awayGoals

  def hasScorer(scorer: Player): Boolean = this.allScorers.contains(scorer)

  def homeGoals: Int = this.homeGoalsNo

  def isAwayWin: Boolean = this.goalDifference < 0

  def isGoalless: Boolean = this.totalGoals == 0

  def isHigherScoringThan(anotherMatch: Match): Boolean = this.totalGoals > anotherMatch.totalGoals

  def isHomeWin: Boolean = this.goalDifference > 0

  def isTied = this.goalDifference == 0

  def location: String = this.home.stadium

  override def toString: String = s"${this.home} vs. ${this.away} at ${this.location}: ${
    if this.isTied then s"tied at ${if this.isGoalless then "nil-nil" else s"${this.homeGoals}-all"}"
    else s"${max(this.homeGoals, this.awayGoals)}-${min(this.homeGoals, this.awayGoals)} to ${this.winnerName}"
  }"

  def totalGoals: Int = this.homeGoals + this.awayGoals

  def winnerName =
    if this.goalDifference < 0 then
      this.away.name
    else if this.goalDifference > 0 then
      this.home.name
    else
      "no winner"

  def winningScorerName: String =
    if this.isTied then "no winning goal"
    else if this.isHomeWin then this.homeScorers(this.homeGoalsNo - this.goalDifference).name
    else this.awayScorers(this.awayGoalsNo + this.goalDifference).name
end Match
