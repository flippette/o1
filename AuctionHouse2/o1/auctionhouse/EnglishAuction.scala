package o1.auctionhouse

class EnglishAuction(description: String,
                     val startingPrice: Int,
                     duration: Int)
  extends ItemForSale(description):
  private var remainingDuration: Int = duration
  private var maxBid: Bid = Bid(None, this.startingPrice)
  private var secondMaxBid: Bid = Bid(None, this.startingPrice)

  def advanceOneDay(): Unit = if this.isOpen then this.remainingDuration -= 1
  def bid(bidder: String, amount: Int): Boolean =
    if !this.isOpen then false
    else if amount >= this.maxBid.limit then
      this.secondMaxBid = this.maxBid
      this.maxBid = Bid(Some(bidder), amount)
      true
    else if amount >= this.requiredBid then
      this.secondMaxBid = Bid(Some(bidder), amount)
      false
    else false
  def buyer: Option[String] = this.maxBid.bidder
  def daysLeft: Int = this.remainingDuration
  def hasNoBids: Boolean = this.maxBid.isInitialBid
  def isExpired: Boolean = this.daysLeft == 0 && this.hasNoBids
  def isOpen: Boolean = this.daysLeft > 0
  def price: Int =
    if this.secondMaxBid.isInitialBid then this.startingPrice
    else if this.secondMaxBid.limit == this.maxBid.limit then this.maxBid.limit
    else this.secondMaxBid.limit + 1
  def requiredBid: Int =
    if this.hasNoBids then this.startingPrice
    else this.price + 1
end EnglishAuction
