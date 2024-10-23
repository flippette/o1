package o1.items

import scala.collection.mutable.Buffer

class Container(name: String) extends Item(name):
  private val contentBuffer: Buffer[Item] = Buffer.empty

  def addContent(item: Item): Unit = this.contentBuffer += item
  def contents: Vector[Item] = this.contentBuffer.toVector

  override def toString: String = s"${this.name} containing ${this.contents.length} item(s)"
end Container
