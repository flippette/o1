package o1.legal

trait NaturalPerson(val personID: String) extends Entity:
  def kind: String = "human"
end NaturalPerson

class FullCapacityPerson(personID: String, name: String)
  extends Entity(name), NaturalPerson(name):
  def contact: NaturalPerson = this

  override def kind: String = s"${super.kind} in full capacity"
end FullCapacityPerson

class ReducedCapacityPerson(
                             personID: String,
                             name: String,
                             val restriction: Restriction,
                             val guardian: FullCapacityPerson
                           ) extends Entity(name), NaturalPerson(personID):
  def contact: NaturalPerson = this.guardian

  override def kind: String = s"${super.kind} with $restriction"
end ReducedCapacityPerson
