package vault.utils

/**
  * Created by alex on 05.11.17.
  */
object CommandReader {

  private val splitSymbol = " "

  def readCommand() : (Command.Value, Array[String]) = {
    val str : Array[String] = scala.io.StdIn.readLine().split(splitSymbol)
    if(str.length > 0) {
      val command = toCommand(str(0))
      return Pair(command, str.drop(1))
    }
    Pair(Command.INVALID, Array())
  }

  private def toCommand(str: String) : Command.Value = {
    str match {
      case "store" => Command.STORE
      case "close" => Command.CLOSE
      case "open" => Command.OPEN
      case "create" => Command.CREATE
      case "read" => Command.READ
      case "status" => Command.STATUS
      case "exit" => Command.EXIT
      case _ => Command.INVALID
    }
  }
}
