package o1.legal

trait JuridicalPerson extends Entity

trait HumanOrganization(val contact: NaturalPerson) extends JuridicalPerson

class Group(val members: Vector[Entity]) extends Entity("group"), JuridicalPerson:
  val leader: Entity = members.head

  def contact: NaturalPerson = this.leader.contact
  def kind: String = s"group of ${this.members.length} led by ${this.leader.name}"
end Group

class GeographicalFeature(name: String, val kind: String, val representative: Entity)
  extends Entity(name), JuridicalPerson:
  def contact: NaturalPerson = this.representative.contact
end GeographicalFeature


class Nation(name: String, contact: NaturalPerson) extends Entity(name), HumanOrganization(contact):
  def kind = "sovereign nation"

class Municipality(name: String, val nation: Nation, contact: NaturalPerson)
      extends Entity(name), HumanOrganization(contact):
  def kind = "municipality of " + this.nation.name

class Corporation(val id: String, val seeksProfit: Boolean, name: String, contact: NaturalPerson)
      extends Entity(name), HumanOrganization(contact):
  def kind = (if this.seeksProfit then "for-" else "non-") + "profit corporation"
