package o1.auctionhouse

trait InstantPurchase extends ItemForSale:
  private var itemBuyer: Option[String] = None

  def buy(buyer: String): Boolean =
    if this.isOpen then
      this.itemBuyer = Some(buyer)
      true
    else
      false
  def buyer: Option[String] = this.itemBuyer
  def isOpen: Boolean = this.itemBuyer.isEmpty && !this.isExpired
end InstantPurchase