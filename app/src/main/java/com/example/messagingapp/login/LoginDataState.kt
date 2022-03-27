package com.example.messagingapp.login

sealed class LoginDataState {
    data class LoginSuccess(val username: String) : LoginDataState()
    object LoggingIn : LoginDataState()
    object LoginFailed : LoginDataState()
}