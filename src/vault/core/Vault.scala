package vault.core

import scala.collection.mutable

/**
  * Created by alex on 04.11.17.
  */
class Vault(val name: String, val vaultKey: String) {

  private var isAuthorized = false
  private val secrets : mutable.HashMap[String, Secret] = mutable.HashMap()

  def addSecret(key: String, secret : Secret) : Unit = {
    if(secrets != null & isAuthorized ) {
      secrets.put(key, secret)
    }
  }

  def getSecret(key: String, name: String) : Option[Secret] = {
    if (secrets != null & isAuthorized) {
      val secretOption = secrets.get(key)
      if(secretOption.isDefined) {
        if(secretOption.get.name.equals(name)) {
          return secretOption
        }
      }
    }
    None
  }

  def authorize(key: String) : Boolean = {
    if(vaultKey.equals(key)) {
      isAuthorized = true
      return true
    }
    false
  }

  def closeVault() : Unit = {
    isAuthorized = false
  }

  def isOpen : Boolean = isAuthorized
}
