package com.example.messagingapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class], version = 1
)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        const val USER_DB_NAME = "users.db"
    }

    abstract fun userDAO(): UserDAO
}