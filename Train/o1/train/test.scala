package o1.train

// Feel free to define one or more main functions here, for testing.

@main def myTest() =
  val car = SittingCar(numberOfRows = 6, seatsPerRow = 5)

  assert(car.reservePlace(numberOfRow = 1, seat = 'a'))
  assert(car.isReservedSeat(numberOfRow = 1, seat = 'a'))
  assert(car.numberOfFreePlaces == 29)
  assert(car.fullness.-(3.33).abs < 0.01)

  assert(car.reservePlace(numberOfRow = 1, seat = 'e'))
  assert(car.isReservedSeat(numberOfRow = 1, seat = 'e'))
  assert(car.numberOfFreePlaces == 28)
  assert(car.fullness.-(6.67).abs < 0.01)

  assert(car.reservePlaces(3))
  assert(car.isReservedSeat(numberOfRow = 1, seat = 'b'))
  assert(car.isReservedSeat(numberOfRow = 1, seat = 'c'))
  assert(car.isReservedSeat(numberOfRow = 1, seat = 'd'))
  assert(car.numberOfFreePlaces == 25)
  assert(car.fullness.-(16.67).abs < 0.01)

  val car2 = SittingCar(numberOfRows = 8, seatsPerRow = 5)

  assert(car2.reservePlace(numberOfRow = 2, seat = 'a'))
  assert(car2.isReservedSeat(numberOfRow = 2, seat = 'a'))
  assert(car2.numberOfFreePlaces == 39)
  assert(car2.fullness.-(2.5).abs < 0.01)

  assert(car2.reservePlace(numberOfRow = 1, seat = 'b'))
  assert(car2.isReservedSeat(numberOfRow = 1, seat = 'b'))
  assert(car2.numberOfFreePlaces == 38)
  assert(car2.fullness.-(5.0).abs < 0.01)

  assert(!car2.reservePlaces(9))
  assert(car2.isReservedSeat(numberOfRow = 2, seat = 'a'))
  assert(car2.isReservedSeat(numberOfRow = 1, seat = 'b'))
  assert(car2.numberOfFreePlaces == 38)
  assert(car2.fullness.-(5.0).abs < 0.01)

  assert(car2.reservePlace(numberOfRow = 8, seat = 'd'))
  assert(car2.isReservedSeat(numberOfRow = 8, seat = 'd'))
  assert(car2.numberOfFreePlaces == 37)
  assert(car2.fullness.-(7.5).abs < 0.01)

  assert(car2.reservePlace(numberOfRow = 3, seat = 'a'))
  assert(car2.isReservedSeat(numberOfRow = 3, seat = 'a'))
  assert(car2.numberOfFreePlaces == 36)
  assert(car2.fullness.-(10).abs < 0.01)

  assert(car2.reservePlace(numberOfRow = 2, seat = 'd'))
  assert(car2.isReservedSeat(numberOfRow = 2, seat = 'd'))
  assert(car2.numberOfFreePlaces == 35)
  assert(car2.fullness.-(12.5).abs < 0.01)

  assert(car2.reservePlaces(3))
  assert(car2.isReservedSeat(numberOfRow = 1, seat = 'c'))
  assert(car2.isReservedSeat(numberOfRow = 1, seat = 'd'))
  assert(car2.isReservedSeat(numberOfRow = 1, seat = 'e'))
  assert(car2.numberOfFreePlaces == 32)
  assert(car2.fullness.-(20).abs < 0.01)
