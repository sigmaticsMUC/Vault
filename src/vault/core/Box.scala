package vault.core

/**
  * Created by alex on 04.11.17.
  */
class Box(val index: Int, val content: Array[Byte]) extends Serializable{


  def getIndex : Int = index

  def getContent : Array[Byte] = content

  override def toString: String = index + "" + content
}
