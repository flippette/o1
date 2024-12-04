package o1.train

/** The class `SittingCar` represents passenger cars with seats in a train ticket reservation
  * system. Each sitting car has rows of seats, which are numbered starting from one upwards.
  * Each row has a number of seats, identified by letters starting from 'a'.
  *
  * When created, all the seats in a sitting car are unreserved. This changes as reservations
  * are made.
  *
  * In this simple implementation, no data is stored about who has reserved which seat.
  *
  * @param numberOfRows the number of seat rows in the car
  * @param seatsPerRow  the number of seats in each row */
class SittingCar(val numberOfRows: Int, val seatsPerRow: Int) extends TrainCar:

  private val seatReservations: Array[Array[Boolean]] =
    Array.ofDim[Boolean](numberOfRows, seatsPerRow) // one-way flags; initially false (unreserved)


  /** Returns the number of places (seats) this car has for passengers. This is a positive number. */
  def numberOfPlaces = this.numberOfRows * this.seatsPerRow


  /** Returns the number of free, unreserved places (seats) in this car. */
  def numberOfFreePlaces = this.seatReservations.map(_.count(!_)).sum

  /** Reserves the indicated seat, if possible. The reservation is unsuccessful if the seat
    * was already reserved. In the latter case, the method returns `false`; it returns `true` otherwise.
    *
    * @param numberOfRow a row number (valid number that is >=1)
    * @param seat        a seat identifier (valid small letter, from 'a' onwards) */
  def reservePlace(numberOfRow: Int, seat: Char) =
    if this.isReservedSeat(numberOfRow, seat) then
      false
    else
      this.seatReservations(numberOfRow - 1)(seat - 'a') = true
      true

  /** Returns a Boolean value indicating whether the indicated seat is reserved or not.
    *
    * @param numberOfRow a row number (valid number that is >=1)
    * @param seat        a seat identifier (valid small letter, from 'a' onwards) */
  def isReservedSeat(numberOfRow: Int, seat: Char) = this.seatReservations(numberOfRow - 1)(seat - 'a')

  /** Reserves places (seats) for a group of people whose size is indicated by the parameter.
    * For a sitting car, a group reservation means that the whole group must get adjacent seats
    * in the same row.
    *
    * Where it would be possible to make the reservation in many different ways, the smallest
    * possible row number and first (alphabetically) possible seat identifiers are selected.
    *
    * If it is not possible to reserve suitable seats for all members of the group, no places
    * are reserved at all. The return value of this method indicates whether the seats were
    * reserved or not. */
  def reservePlaces(numberOfPeople: Int) =
    var seatsFound = false // one-way flag
    var rowIndex = 0 // stepper

    while rowIndex < this.numberOfRows do
      var seatIndex = 0 // stepper
      while seatIndex < this.seatsPerRow && !seatsFound do
        val adjacentsFound = this.findAdjacents(rowIndex, seatIndex, numberOfPeople) // temporary
        if adjacentsFound == numberOfPeople then
          this.reserveAdjacents(rowIndex, seatIndex, numberOfPeople)
          seatsFound = true
        seatIndex += adjacentsFound + 1
      end while
      rowIndex += 1
    end while

    seatsFound

  end reservePlaces


  /** Checks whether the given number of adjacent seats are free at the given spot in the
    * car. (Note: “Adjacent seats” includes both the given spot and any others next to it.
    * So if the seat at the given spot is free, this method will return a positive number.)
    *
    * @param rowIndex      a valid row index (>=0)
    * @param seatIndex     a valid seat index (>=0)
    * @param numberOfSeats the desired number of adjacent seats
    * @return the number of adjacent seats starting at the seat indicated by the
    *         parameter values; this will be a number between 0 and `numberOfSeats`, inclusive */
  private def findAdjacents(rowIndex: Int, seatIndex: Int, numberOfSeats: Int) =

    var adjacents = 0 // stepper
    var checkingIndex = seatIndex // stepper

    while adjacents < numberOfSeats && checkingIndex < this.seatsPerRow && !this.seatReservations(rowIndex)(checkingIndex) do
      adjacents += 1
      checkingIndex += 1

    adjacents

  end findAdjacents


  /** Reserves the given number of adjacent seats starting at the given seat.
    * Assumes that those seats are free to begin with.
    *
    * @param rowIndex      a valid row index (>=0)
    * @param seatIndex     a valid seat index (>=0)
    * @param numberOfSeats the number of seats to reserve, starting with the one indicated by the other parameters */
  private def reserveAdjacents(rowIndex: Int, seatIndex: Int, numberOfSeats: Int) =
    for toBeReservedIndex <- 0 until numberOfSeats do // stepper
      this.seatReservations(rowIndex)(seatIndex + toBeReservedIndex) = true


end SittingCar

