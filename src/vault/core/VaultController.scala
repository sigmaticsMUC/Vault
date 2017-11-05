package vault.core

import scala.collection.mutable

/**
  * Created by alex on 05.11.17.
  */
object VaultController {

  private val vaults : mutable.HashMap[String, Vault] = mutable.HashMap()


  def addNewVault(name: String, key: String) : Unit = {
    vaults.put(name, new Vault(name, key))
  }

  def addNewVault(vault: Vault) : Unit = {
    vaults.put(vault.name, vault)
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
