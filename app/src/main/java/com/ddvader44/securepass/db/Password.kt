package com.ddvader44.securepass.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password (
    @PrimaryKey(autoGenerate = true) val uid : Int=1,
    val type : String,
    val hashed : String
)
