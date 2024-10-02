package o1.blood1

class BloodType(val abo: String, val rhesus: Boolean):

  override def toString = s"${this.abo}${if this.rhesus then "+" else "-"}"

  def hasSafeABOFor(recipient: BloodType) =
    this.abo == "O" || recipient.abo == "AB" || this.abo == recipient.abo
  def hasSafeRhesusFor(recipient: BloodType) = !this.rhesus || recipient.rhesus

  def canDonateTo(recipient: BloodType) =
    this.hasSafeABOFor(recipient) && this.hasSafeRhesusFor(recipient)
  def canReceiveFrom(donor: BloodType) = donor.canDonateTo(this)

end BloodType

