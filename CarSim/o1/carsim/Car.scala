package o1.carsim

import o1.Pos
import scala.math.{min, max, sqrt}

class Car(val fuelConsumption: Double, val tankSize: Double, initialFuel: Double, initialLocation: Pos):
  private var currentLocation = this.initialLocation
  private var fuelLevel = this.initialFuel
  private var meters = 0.0
  private val fuelConsumptionPerMeter = this.fuelConsumption / 100 / 1000

  def location: Pos = this.currentLocation

  def fuel(toBeAdded: Double): Double =
    val prevLevel = this.fuelLevel
    this.fuelLevel = min(this.tankSize, this.fuelLevel + toBeAdded)
    this.fuelLevel - prevLevel
  def fuel(): Double = this.fuel(Double.MaxValue)

  def fuelRatio: Double = this.fuelLevel / this.tankSize * 100
  def metersDriven: Double = this.meters
  def fuelRange: Double = this.fuelLevel / this.fuelConsumptionPerMeter

  def drive(destination: Pos, metersToDestination: Double): Unit =
    val range = this.fuelRange
    val ratio = min(1, range / metersToDestination)
    val travel = (destination - this.location) * ratio
    this.currentLocation += travel
    this.meters += metersToDestination * ratio
    this.fuelLevel -= metersToDestination * this.fuelConsumptionPerMeter * ratio
end Car

