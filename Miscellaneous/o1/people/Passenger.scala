package o1.people

class Passenger(val name: String, val card: Option[TravelCard]):
  def canTravel: Boolean = card.exists(_.isValid)
  def hasCard: Boolean = card.isDefined
end Passenger