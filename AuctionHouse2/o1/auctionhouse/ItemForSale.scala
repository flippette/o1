package o1.auctionhouse

trait ItemForSale(val description: String):
  def advanceOneDay(): Unit
  def buyer: Option[String]
  def isExpired: Boolean
  def isOpen: Boolean
  def price: Int

  override def toString: String = this.description
end ItemForSale