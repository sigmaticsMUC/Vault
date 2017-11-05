package vault.core

import scala.collection.mutable.ListBuffer

/**
  * Created by alex on 04.11.17.
  */
class Secret(val key: String) {

  private val boxes : ListBuffer[Secret] = new ListBuffer[Secret]

  def getBoxes : ListBuffer[Secret] = boxes

}
