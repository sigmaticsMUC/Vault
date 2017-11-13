package vault.core

import vault.factory.SecretFactory
import vault.utils.{Command, CommandReader}

/**
  * Created by alex on 04.11.17.
  */
object Controller {

  def main(args : Array[String]): Unit = {
    var command : (Command.Value, Array[String]) = (Command.INVALID, Array())
    while(true) {
      print("Vault 1.0 > ")
      command = CommandReader.readCommand()
      execute(command._1, command._2)
    }
  }

  def execute(command: Command.Value, args: Array[String]): Unit = {
    command match {
      case Command.CREATE => createVault(args)
      case Command.OPEN => openVault(args)
      case Command.CLOSE => closeVault(args)
      case Command.STORE => storeSecret(args)
      case Command.READ => readSecret(args)
      case Command.STATUS => status()
      case Command.EXIT => exit()
      case _ => invalid()
    }
  }

  def createVault(args: Array[String]) : Unit = {
    val name : String = args(0)
    val password: String = args(1)
    val vault : Vault = new Vault(name, password)
    vault.authorize(password)
    //VaultController.addNewVault(name, password)
    VaultController.addNewVault(vault)
  }

  def openVault(args: Array[String]) : Unit = {
    val name : String = args(0)
    val password: String = args(1)
    if(!VaultController.openVault(name, password)) {
      Console.err.println("\tAccess to vault denied! Check password or name\n")
    }
  }

  def closeVault(args: Array[String]) : Unit = {
    val name : String = args(0)
    VaultController.closeVault(name)
  }

  def storeSecret(args: Array[String]) : Unit = {
    val vaultName : String = args(0)
    val secretName : String = args(1)
    val pathToSecret : String = args(2)
    val secretKey : String = args(3)
    VaultController.storeSecret(vaultName, pathToSecret, secretKey, secretName)
  }

  def readSecret(args: Array[String]) : Unit = {

  }

  def status() : Unit = {
    println(VaultController.getVaultStatus)
  }

  def exit() : Unit = {
    println("Vault says good bye :)")
    System.exit(0)
  }

  def invalid() : Unit = {
    Console.err.println("\n\tInvalid command boi!\n")
  }

}

