package com.ddvader44.securepass.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PasswordDao {

    @Query("SELECT * from password")
    fun getAllPassword()  : List<Password>

    @Insert
    fun insertPassword(password: Password)

    @Delete
    fun deletePassword(password: Password)

}