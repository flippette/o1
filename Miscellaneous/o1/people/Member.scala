package o1.people

class Member(val id: Int, val name: String, val yearOfBirth: Int, val yearOfDeath: Option[Int]):
  def isAlive: Boolean = this.yearOfDeath.isEmpty
  override def toString: String = s"${this.name}(${this.yearOfBirth}-${this.yearOfDeath.map(_.toString).getOrElse("")})"
end Member
