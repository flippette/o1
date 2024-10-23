package o1.auctionhouse

import scala.collection.mutable.Buffer

class AuctionHouse(val name: String):
  private val items: Buffer[ItemForSale] = Buffer.empty

  def addItem(item: ItemForSale): Unit = this.items += item
  def averagePrice: Double = this.totalPrice.toDouble / this.items.size
  def nextDay(): Unit = this.items.foreach(_.advanceOneDay())
  def numberOfOpenItems: Int = this.items.count(_.isOpen)
  def priciest: Option[ItemForSale] = this.items.maxByOption(_.price)
  def purchasesOf(buyer: String): Vector[ItemForSale] =
    this.items.filter(_.buyer == Some(buyer)).toVector
  def removeItem(item: ItemForSale): Unit =
    this.items.remove(this.items.indexOf(item))
  override def toString: String =
    s"${this.name}:\n${(this.items.mkString("\n"))}"
  def totalPrice: Int = this.items.map(_.price).sum
end AuctionHouse