package com.ddvader44.securepass.viewmodels

import androidx.lifecycle.ViewModel
import com.ddvader44.securepass.algos.Argon2Id
import com.ddvader44.securepass.algos.BCrypt
import com.ddvader44.securepass.algos.PBKFD2
import java.security.MessageDigest

class HomeViewModel : ViewModel() {

    fun getHash(plainText: String, algorithm: String) : String {

        when (algorithm) {
            "Social Media" -> {
                return pbkfd2algo(plainText)
            }
            "Email" -> {
                val bytes = MessageDigest.getInstance("MD5").digest(plainText.toByteArray())
                return toHex(bytes)
            }
            "Online Certifications" -> {
                return bcryptalgo(plainText)
            }
            "Cloud Servers" -> {
                return argonalgo(plainText)
            }
            else -> {
                return "doing"
            }
        }
    }

    private fun toHex(byteArray: ByteArray): String {
        return byteArray.joinToString("") { "%02x".format(it) }
    }

    private fun pbkfd2algo(plainText: String) : String{
        return PBKFD2.pbkfd(plainText)
    }
    private fun bcryptalgo(plainText: String) : String{
        return BCrypt.bcrypt(plainText)
    }
    private fun argonalgo(plainText: String) : String{
        return Argon2Id.argon(plainText)
    }

}
