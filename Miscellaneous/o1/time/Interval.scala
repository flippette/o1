package o1.time

class Interval(val start: Moment, val end: Moment):
  def contains(moment: Moment): Boolean = moment.isIn(this)
  def contains(another: Interval): Boolean = another.start.isIn(this) && another.end.isIn(this)
  def intersection(another: Interval): Option[Interval] =
    if this.start.isLaterThan(another.end) || another.start.isLaterThan(this.end) then None
    else Some(Interval(this.start.later(another.start), this.end.earlier(another.end)))
  def isLaterThan(moment: Moment): Boolean = this.start.isLaterThan(moment)
  def isLaterThan(another: Interval): Boolean = this.isLaterThan(another.end)
  def length: Int = this.start.distance(this.end)
  def overlaps(another: Interval): Boolean =
    this.contains(another.start) || this.contains(another.end)
      || another.contains(this.start) || another.contains(this.end)
  override def toString: String =
    if this.length == 0 then s"${this.start}"
    else if this.length <= 50 then s"${this.start}${"-" * this.length}${this.end}"
    else s"${this.start}...${this.end}"
  def union(another: Interval): Interval =
    Interval(this.start.earlier(another.start), this.end.later(another.end))
end Interval