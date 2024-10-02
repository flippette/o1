package o1.carsim

import o1.Pos


private def printReport(car: Car) =
  println("Now at "      + car.location)
  println("Fuel ratio: " + car.fuelRatio    + "%")
  println("Fuel range: " + car.fuelRange    +" m")
  println("Driven: "     + car.metersDriven + " m")

/** A small program that uses the class `Car`. Students are free to extend and customize it. */
@main def carTest() =

  val testCar = Car(10.0, 200.0, 35.0, Pos(-100, 200)) // using (weird) round figures for simplicity
  println("Car created.")
  printReport(testCar)

  println("\nAdding 5 liters.")
  testCar.fuel(5)
  printReport(testCar)

  println("\nDriving.")
  // This 1000 km drive drains all the fuel, which is only enough for 40% of the trip. Should end up at (-140.0,180.0).
  testCar.drive(Pos(-200, 150), 1000000)
  printReport(testCar)

  // Modify the above and/or add your own test code here, if you wish.


end carTest
