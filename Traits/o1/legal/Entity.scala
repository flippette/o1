package o1.legal

trait Entity(val name: String):
  def contact: NaturalPerson
  def kind: String
  override def toString: String = s"${this.name} (${this.kind})"
end Entity
