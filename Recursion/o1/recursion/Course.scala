package o1.recursion

// This code is introduced in Chapter 12.2.

abstract class Course(val name: String, val cr: Int):
  def totalCredits: Int
  override def toString = s"$name ($cr)"


class IntroCourse(name: String, cr: Int) extends Course(name, cr):
  def totalCredits = this.cr


class FollowOnCourse(name: String, cr: Int, val prerequisite: Course) extends Course(name, cr):
  def totalCredits = this.prerequisite.totalCredits + this.cr



object AlternativeImplementationWithOption:

  class Course(val name: String, val cr: Int, val prerequisite: Option[Course]):
    def totalCredits: Int = this.cr + this.prerequisite.map( _.totalCredits  ).getOrElse(0)
    override def toString = s"$name ($cr)"

end AlternativeImplementationWithOption

