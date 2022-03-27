package com.example.messagingapp.di

import com.example.messagingapp.Repo
import com.example.messagingapp.db.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Singleton
    @Provides
    fun provideRepo(
        userDao: UserDAO
    ) = Repo(userDao)
}