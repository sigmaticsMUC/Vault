package vault.factory

import java.io.{File, FileOutputStream, FileWriter, ObjectOutputStream}

import vault.core.{Box, Secret}
import java.nio.file.{Files, Paths}

import scala.collection.mutable.ListBuffer

/**
  * Created by alex on 04.11.17.
  */
object SecretFactory {

  def createSecret(vaultName: String, path: String, key: String, name: String) : Option[Secret] = {
    if(Files.exists(Paths.get(path))) {
      //val file : File = new File(path)
      val boxes: ListBuffer[Box] = createBoxes(path)
      val secret : Secret = new Secret(key, name)
      val dir : File = new File("./res/"+vaultName+"/"+name)

      dir.mkdir()
      println(dir.getAbsolutePath)
      for(box <- boxes) {
        secret.addBox(box)
        val randomInt : Int = scala.util.Random.nextInt(boxes.length+1)
        val writer = new ObjectOutputStream(new FileOutputStream(dir.getAbsolutePath+"/"+randomInt))
        writer.writeObject(box)
        writer.flush()
        writer.close()
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
    val byteArray = Files.readAllBytes(Paths.get(path))
    for(index <- 0 to byteArray.length - 1) {
      boxes.append(new Box(index, byteArray(index)))
    }
    util.Random.shuffle(boxes)
  }

}
