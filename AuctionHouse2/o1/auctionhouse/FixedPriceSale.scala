package o1.auctionhouse

import scala.math.max

class FixedPriceSale(description: String, val price: Int, duration: Int)
  extends ItemForSale(description), InstantPurchase:
  private var remainingDuration: Int = duration

  def advanceOneDay(): Unit =
    if this.isOpen then
      this.remainingDuration = max(this.remainingDuration - 1, 0)
  def daysLeft: Int = this.remainingDuration
  def isExpired: Boolean = this.daysLeft == 0
end FixedPriceSale