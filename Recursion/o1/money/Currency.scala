package o1.money

class Currency(val coinValues: Vector[Int]):
  assert(coinValues.sorted == coinValues)

  def numberOfCoinTypes = this.coinValues.size

  def waysToSplit(sum: Int): Int =
    def waysToSplitWith(sum: Int, coins: Vector[Int]): Int =
      if coins.isEmpty then 0
      else
        val maxCoinSplits = 0 to sum / coins.last
        maxCoinSplits.length + maxCoinSplits.map(sum.-).map(waysToSplitWith(_, coins.dropRight(1))).sum

    waysToSplitWith(sum, this.coinValues)


  override def toString = this.coinValues.mkString(",")

end Currency


val EUR = Currency(Vector(1, 2, 5, 10, 20, 50, 100, 200))
val USD = Currency(Vector(1, 5, 10, 25, 50))
