package o1.time
import scala.math.*

/** Each instance of the class `Moment` represents a “moment” on a timescale,
  * identified by an integer number.
  *
  * Different timescales may be used in different contexts. `Moment` objects
  * could be used to represent different years, months, dates, hours, etc.,
  * depending on what is desired.
  *
  * A `Moment` object is immutable after it has been created. That is, its
  * state can not be changed in any way.
  *
  * @param time  an integer indicating which moment the object should represent (2024, for instance) */
class Moment(private val time: Int):
  def distance(another: Moment): Int = abs(this.time - another.time)
  def earlier(another: Moment): Moment = if this.isLaterThan(another) then another else this
  def isIn(interval: Interval): Boolean = interval.start.time <= this.time && this.time <= interval.end.time
  def isLaterThan(another: Moment): Boolean = this.time > another.time
  def isLaterThan(interval: Interval): Boolean = this.isLaterThan(interval.end)
  def later(another: Moment): Moment = if this.isLaterThan(another) then this else another
  override def toString: String = s"${this.time}"
end Moment

