package o1.auctionhouse

import scala.math.*

class EnglishAuction(val description: String, val startingPrice: Int, duration: Int):
  private var highestBid = Bid(None, startingPrice)
  private var secondHighestBid = Bid(None, startingPrice)
  private var remainingDays = duration

  def daysLeft: Int = this.remainingDays
  def hasNoBids: Boolean = this.highestBid.bidder.isEmpty
  def advanceOneDay(): Unit = if this.isOpen then this.remainingDays -= 1
  def isOpen: Boolean = this.remainingDays > 0
  def isExpired: Boolean = !this.isOpen && this.hasNoBids
  def buyer: Option[String] = this.highestBid.bidder
  def price: Int =
    if this.secondHighestBid.bidder.isEmpty then this.startingPrice
    else if this.highestBid.limit == this.secondHighestBid.limit then this.highestBid.limit
    else min(this.highestBid.limit, this.secondHighestBid.limit) + 1
  def requiredBid: Int = if this.hasNoBids then this.startingPrice else this.price + 1
  def bid(bidder: String, amount: Int): Boolean =
    if !this.isOpen then
      false
    else if amount > this.highestBid.limit then
      val lastHighest = this.highestBid
      this.highestBid = Bid(Some(bidder), amount)
      this.secondHighestBid = lastHighest
      true
    else if amount > this.secondHighestBid.limit then
      this.secondHighestBid = Bid(Some(bidder), amount)
      false
    else
      false
  override def toString = this.description
end EnglishAuction
