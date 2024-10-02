package o1.auctionhouse

class DutchAuction(
  val description: String,
  val startingPrice: Int,
  val decrement: Int,
  val minimumPrice: Int
):
  private var currentPrice: Int = startingPrice
  private var auctionBuyer: Option[String] = None
  private var stagnantDays: Int = 0

  def advanceOneDay(): Unit =
    if this.isOpen then
      if this.currentPrice - this.decrement >= this.minimumPrice then
        this.currentPrice -= this.decrement
      else if this.currentPrice > this.minimumPrice then
        this.currentPrice = this.minimumPrice
      else
        this.stagnantDays += 1
  def buy(buyer: String): Boolean =
    if this.isOpen then
      this.auctionBuyer = Some(buyer)
      true
    else
      false
  def buyer: Option[String] = this.auctionBuyer
  def isExpired: Boolean = this.stagnantDays > 3
  def isOpen: Boolean = !this.isExpired && this.buyer.isEmpty
  def price: Int = this.currentPrice
  def priceRatio: Double = this.price.toDouble / this.startingPrice.toDouble
  override def toString: String = this.description
end DutchAuction