package com.example.messagingapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM user_tbl WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String) : UserEntity?
}
