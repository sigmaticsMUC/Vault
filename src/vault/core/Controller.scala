package vault.core

import vault.factory.SecretFactory

/**
  * Created by alex on 04.11.17.
  */
object Controller {

  def main(args : Array[String]) : Unit = {

    val vault : Vault = openVault("./Vault", "abcdef")
    SecretFactory.sayHello()
  }


  def openVault(path : String, passKey: String): Vault = {
    return new Vault()
  }

  def closeVault(path : String, passKey: String): Unit = {

  }

}

