package com.ddvader44.securepass.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ddvader44.securepass.algos.Argon2Id
import com.ddvader44.securepass.algos.BCrypt
import com.ddvader44.securepass.algos.PBKFD2
import java.security.MessageDigest

class HomeViewModel : ViewModel() {

    fun getHash(plainText: String, algorithm: String) : String {

        val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
        return toHex(bytes)

    }

    private fun toHex(byteArray: ByteArray): String {
        Log.d("ViewModel",byteArray.joinToString { "%02x".format(it) })
        return byteArray.joinToString { "%02x".format(it) }
    }

    fun pbkfd2algo(plainText: String) : String{
        return PBKFD2.pbkfd(plainText)
    }
    fun bcryptalgo(plainText: String) : String{
        return BCrypt.bcrypt(plainText)
    }
    fun argonalgo(plainText: String) : String{
        return Argon2Id.argon(plainText)
    }

}
