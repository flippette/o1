package o1.blood3

trait BloodType[System <: BloodType[System]]:
  this: System =>

  def canDonateTo(recipient: System): Boolean
  def canReceiveFrom(donor: System): Boolean = donor.canDonateTo(this)

enum Rhesus(val isPositive: Boolean) extends BloodType[Rhesus]:
  case RhPlus extends Rhesus(true)
  case RhMinus extends Rhesus(false)

  def isNegative = !this.isPositive
  def canDonateTo(recipient: Rhesus) = this.isNegative || this == recipient
  override def toString = if this.isPositive then "+" else "-"
end Rhesus

enum ABO(val antigens: String) extends BloodType[ABO]:
  case A extends ABO("A")
  case B extends ABO("B")
  case AB extends ABO("AB")
  case O extends ABO("")

  def canDonateTo(recipient: ABO): Boolean = (this, recipient) match
    case (O, _) | (_, AB) => true
    case (donor, recip) if (donor == recip) => true
    case _ => false
end ABO

class ABORh(val abo: ABO, val rhesus: Rhesus) extends BloodType[ABORh]:
  def canDonateTo(recipient: ABORh) =
    this.abo.canDonateTo(recipient.abo) && this.rhesus.canDonateTo(recipient.rhesus)
  override def toString = this.abo.toString + this.rhesus.toString
end ABORh
