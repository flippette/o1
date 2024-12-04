package o1.football4

class Match(val home: Club,
            val away: Club,
            val homeScorers: Vector[Player],
            val awayScorers: Vector[Player]):
  def addGoal(scorer: Player): Match =
    scorer.employer match
      case `home` => Match(this.home, this.away, this.homeScorers :+ scorer, this.awayScorers)
      case `away` => Match(this.home, this.away, this.homeScorers, this.awayScorers :+ scorer)
      case _ => this

  def goalDifference: Int = math.abs(this.awayGoals - this.homeGoals)

  def hasScorer(possibleScorer: Player): Boolean =
    this.allScorers.contains(possibleScorer)

  def allScorers: Vector[Player] = this.homeScorers ++ this.awayScorers

  def isGoalless: Boolean = this.totalGoals == 0

  def isHigherScoringThan(anotherMatch: Match): Boolean = this.totalGoals > anotherMatch.totalGoals

  def totalGoals: Int = this.homeGoals + this.awayGoals

  override def toString: String =
    s"${this.home.name} vs. ${this.away.name} at ${this.location}: ${
      (this.homeGoals, this.awayGoals) match
        case (a, b) if a == b => s"tied at $a-all"
        case (a, b) if a > b => s"$a-$b to ${this.home.name}"
        case (a, b) if a < b => s"$b-$a to ${this.away.name}"
    }"

  def location: String = this.home.stadium

  def winnerName: String = this.winner.map(_.name).getOrElse("no winner")

  def winner: Option[Club] =
    if this.isHomeWin then
      Some(this.home)
    else if this.isTied then
      None
    else Some(this.away)

  def winningScorer: Option[Player] =
    if this.isHomeWin then
      Some(this.awayScorers(this.homeGoals))
    else if this.isTied then
      None
    else Some(this.homeScorers(this.awayGoals))

  def isTied: Boolean = this.homeGoals == this.awayGoals

  def awayGoals: Int = this.awayScorers.length

  def homeGoals: Int = this.homeScorers.length

  def isHomeWin: Boolean = this.homeGoals > this.awayGoals

  def isAwayWin: Boolean = this.awayGoals > this.homeGoals
end Match