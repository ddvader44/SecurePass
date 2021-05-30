package com.ddvader44.securepass.db

import androidx.room.*

@Dao
interface PasswordDao {

    @Query("SELECT * from password")
    fun getAllPassword()  : List<Password>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPassword(password: Password)

    @Delete
    fun deletePassword(password: Password)

}