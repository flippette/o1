package o1.auctionhouse

class FixedPriceSale(val description: String, val price: Int, duration: Int):
  private var daysRemaining: Int = duration
  private var saleBuyer: Option[String] = None

  def advanceOneDay(): Unit = if this.isOpen then daysRemaining -= 1
  def buy(buyer: String): Boolean =
    if this.isOpen then
      this.saleBuyer = Some(buyer)
      true
    else
      false
  def buyer: Option[String] = this.saleBuyer
  def daysLeft: Int = this.daysRemaining
  def isExpired: Boolean = this.daysRemaining <= 0
  def isOpen: Boolean = !this.isExpired && this.buyer.isEmpty
  override def toString: String = this.description
end FixedPriceSale