package com.example.messagingapp

import android.util.Log
import com.example.messagingapp.db.UserDAO
import com.example.messagingapp.db.UserEntity
import javax.inject.Inject


class Repo @Inject constructor(private val userDao: UserDAO) {

    suspend fun registerUser(username: String, password: String) {
        if (userExists(username, password)) {
            Log.d(this::class.simpleName, "User already exists! Registering new pwd")
        }
        userDao.addUser(UserEntity(username, password))
    }

    suspend fun userExists(username: String, password: String): Boolean {
        val foundUser = userDao.getUser(username, password)

        return (foundUser != null)
    }
}
