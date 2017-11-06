package vault.factory

import java.io.{File, FileInputStream}

import vault.core.{Box, Secret}
import java.nio.file.{Files, Paths}

import scala.collection.mutable.ListBuffer

/**
  * Created by alex on 04.11.17.
  */
object SecretFactory {

  def createSecret(path: String, key: String) : Option[Secret] = {
    if(Files.exists(Paths.get(path))) {
      //val file : File = new File(path)
      val boxes: ListBuffer[Box] = createBoxes(path)
      val secret : Secret = new Secret(key)
      for(box <- boxes) {
        secret.addBox(box)
      }
      return Some(secret)
    }
    else {
      println("\t Cant store secret... File does not exist")
      None
    }
  }

  private def createBoxes(path: String) : ListBuffer[Box] = {
    val boxes : ListBuffer[Box] = ListBuffer()
    val byteArray = Files.readAllBytes(Paths.get("/path/to/file"))
    for(index <- 0 to byteArray.length) {
      boxes.append(new Box(index, byteArray(index)))
    }
    util.Random.shuffle(boxes)
  }

}
