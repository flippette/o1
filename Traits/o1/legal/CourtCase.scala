package o1.legal

class CourtCase(val plaintiff: Entity, val defendant: Entity):
  override def toString: String = s"${plaintiff.name} v. ${defendant.name}"
end CourtCase