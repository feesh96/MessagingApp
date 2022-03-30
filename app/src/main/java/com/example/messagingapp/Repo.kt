package com.example.messagingapp

import android.util.Log
import com.example.messagingapp.db.UserDAO
import com.example.messagingapp.db.UserEntity
import javax.inject.Inject


class Repo @Inject constructor(private val userDao: UserDAO) {

    suspend fun registerUser(username: String, password: String) {
        userDao.getUser(username, password)?.let {
            Log.d(this::class.simpleName, "User already exists! logging in")
        } ?: userDao.addUser(UserEntity(username, password))
    }

    suspend fun userExists(username: String, password: String): Boolean {
        val user = userDao.getUser(username, password)
        return (user != null)
    }
}
