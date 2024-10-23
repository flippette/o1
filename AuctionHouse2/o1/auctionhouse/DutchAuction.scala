package o1.auctionhouse

import scala.math.max

class DutchAuction(description: String,
                   val startingPrice: Int,
                   val decrement: Int,
                   val minimumPrice: Int)
  extends ItemForSale(description), InstantPurchase:
  private var currentPrice: Int = startingPrice
  private var daysStagnant: Int = 0

  def advanceOneDay(): Unit =
    if this.isOpen then
      if this.currentPrice == this.minimumPrice then
        this.daysStagnant += 1
      else
        this.currentPrice = max(
          this.currentPrice - this.decrement,
          this.minimumPrice
        )
  def isExpired: Boolean = this.daysStagnant > 3
  def price: Int = this.currentPrice
  def priceRatio: Double = this.price.toDouble / this.startingPrice.toDouble
end DutchAuction
