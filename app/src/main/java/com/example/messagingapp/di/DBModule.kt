package com.example.messagingapp.di

import android.content.Context
import androidx.room.Room
import com.example.messagingapp.db.UserDatabase
import com.example.messagingapp.db.UserDatabase.Companion.USER_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun provideUserDB(@ApplicationContext appContext: Context): UserDatabase =
        Room.databaseBuilder(appContext, UserDatabase::class.java, USER_DB_NAME).build()

    @Singleton
    @Provides
    fun provideUserDao(userDb: UserDatabase) =
        userDb.userDAO()
}