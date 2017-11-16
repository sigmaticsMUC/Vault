package vault.core

import scala.collection.mutable.ListBuffer

/**
  * Created by alex on 04.11.17.
  */
class Secret(val key: String, val name: String) {

  private val boxes : ListBuffer[Box] = new ListBuffer[Box]

  def getBoxes : ListBuffer[Box] = boxes

  def getName : String = name


  def addBox(box: Box) : Unit = {
    boxes.append(box)
  }

}
