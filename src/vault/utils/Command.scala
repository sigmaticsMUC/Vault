package vault.utils

/**
  * Created by alex on 05.11.17.
  */
object Command extends Enumeration {
  val STORE, READ, CREATE, OPEN, CLOSE, STATUS,INVALID, EXIT = Value
}