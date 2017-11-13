package vault.core

import java.io.File

import vault.factory.SecretFactory

import scala.collection.mutable

/**
  * Created by alex on 05.11.17.
  */
object VaultController {

  private val vaults : mutable.HashMap[String, Vault] = mutable.HashMap()


  def addNewVault(name: String, key: String) : Unit = {
    vaults.put(name, new Vault(name, key))
    val dir : File = new File("./res/"+name)
    dir.mkdir()
    println(dir.getAbsolutePath)
  }

  def addNewVault(vault: Vault) : Unit = {
    vaults.put(vault.name, vault)
    val dir : File = new File("./res/"+vault.name)
    dir.mkdir()
    println(dir.getAbsolutePath)
  }

  def closeVault(name: String) : Unit = {
    val vaultOption = vaults.get(name)
    if(vaultOption.isDefined) {
      vaultOption.get.closeVault()
    }
  }

  def openVault(name: String, password: String) : Boolean = {
    val vaultOption = vaults.get(name)
    if(vaultOption.isDefined) {
      val success : Boolean = vaultOption.get.authorize(password)
      if(success) {
        return true
      }
      else {
        return false
      }
    }
    false
  }

  def storeSecret(name: String, pathToSecret: String, secretKey: String, secretName: String) : Unit = {
    val vaultOption = vaults.get(name)
    if(vaultOption.isDefined) {
      val vault = vaultOption.get
      if(vault.isOpen) {
        val secret : Option[Secret] = SecretFactory.createSecret(name, pathToSecret, secretKey, secretName)
        if(secret.isDefined) {
          vault.addSecret(secretKey, secret.get)
        }
      }
      else {
        println("\tCant store secret... Vault closed")
      }
    }
    else {
      println("\tCant store secret... Vault does not exist")
    }
  }

  def getVaultStatus : String = {
    var numVault : Int = vaults.size
    var str: String = ""
    for((name, vault) <- vaults) {
      if(vault.isOpen) {
        numVault -= 1
        str += "\t" + name + " is open!\n"
      }
    }
    if(numVault > 0) {
      str += "\t" + numVault + " are closed!\n"
    }
    str
  }

}
