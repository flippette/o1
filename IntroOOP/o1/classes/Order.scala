package o1.classes

// This class is introduced in Chapter 2.6.

class Order(val number: Int, val orderer: Customer, val address: Option[String]):
  var totalPrice = 0.0   // gatherer

  def addProduct(pricePerUnit: Double, numberOfUnits: Int) =
    this.totalPrice = this.totalPrice + pricePerUnit * numberOfUnits

  override def toString =
    s"order ${this.number}, ordered by ${this.orderer}, total ${this.totalPrice} euro, deliver to ${this.address.getOrElse("customer's address")}"
  def deliveryAddress: String = address.getOrElse(this.orderer.address)
end Order

