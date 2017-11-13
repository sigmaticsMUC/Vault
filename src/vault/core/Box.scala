package vault.core

/**
  * Created by alex on 04.11.17.
  */
class Box(val index: Int, val content: Byte) extends Serializable{


  def getIndex : Int = index

  def getContent : Byte = content

  override def toString: String = index + "" + content
}
