package com.ddvader44.securepass.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey(autoGenerate = true) var uid: Int? =1,
    var type: String,
    var hashed: String,
    var app : String
)
