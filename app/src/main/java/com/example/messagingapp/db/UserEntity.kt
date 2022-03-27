package com.example.messagingapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tbl")
data class UserEntity(
    @PrimaryKey val username: String,
    val password: String
)