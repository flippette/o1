package o1.blood2

enum Rhesus(val isPositive: Boolean):
  case RhPlus extends Rhesus(true)
  case RhMinus extends Rhesus(false)

  def isNegative = !this.isPositive
  def canDonateTo(recipient: Rhesus) = this.isNegative || this == recipient
  def canReceiveFrom(donor: Rhesus) = donor.canDonateTo(this)
  override def toString = if this.isPositive then "+" else "-"
end Rhesus

enum ABO(val antigens: String):
  case A extends ABO("A")
  case B extends ABO("B")
  case AB extends ABO("AB")
  case O extends ABO("")

  def canDonateTo(recipient: ABO): Boolean = (this, recipient) match
    case (O, _) | (_, AB) => true
    case (donor, recip) if (donor == recip) => true
    case _ => false
  def canReceiveFrom(donor: ABO): Boolean = donor.canDonateTo(this)

class ABORh(val abo: ABO, val rhesus: Rhesus):
  def canDonateTo(recipient: ABORh) =
    this.abo.canDonateTo(recipient.abo) && this.rhesus.canDonateTo(recipient.rhesus)
  def canReceiveFrom(donor: ABORh) = donor.canDonateTo(this)
  override def toString = this.abo.toString + this.rhesus.toString