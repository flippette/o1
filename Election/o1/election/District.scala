package o1.election

import scala.collection.mutable.Buffer           // This is useful in early versions of the class.
import scala.math.Ordering.Double.TotalOrdering  // This will be useful in later assignments.

// Write your code here.

class District(val name: String, val seats: Int, val candidates: Vector[Candidate]):
  def candidatesFrom(party: String): Vector[Candidate] = this.candidates.filter(_.party == party)
  def printCandidates() = this.candidates.foreach(println(_))
  override def toString = s"${this.name}: ${this.candidates.size} candidates, ${this.seats} seats"
  def topCandidate: Option[Candidate] = this.candidates.maxByOption(_.votes)
  def totalVotes: Int = this.candidates.map(_.votes).sum
  def totalVotes(party: String): Int = this.candidates.filter(_.party == party).map(_.votes).sum
end District