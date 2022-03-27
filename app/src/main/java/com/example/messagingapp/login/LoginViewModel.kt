package com.example.messagingapp.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messagingapp.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    private val _loginInfo: MutableLiveData<LoginDataState> = MutableLiveData()
    val loginInfo: LiveData<LoginDataState>
        get() = _loginInfo


    fun tryLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.userExists(username, password)) {
                _loginInfo.postValue(LoginDataState.LoginSuccess(username))
                Log.d(this::class.simpleName, "Found user!")
            } else {
                _loginInfo.postValue(LoginDataState.LoginFailed)
                Log.d(this::class.simpleName, "No user found!")
            }
        }
    }

    fun registerUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.registerUser(username, password)
            _loginInfo.postValue(LoginDataState.LoginSuccess(username))
            Log.d(this::class.simpleName, "User registered!")
        }
    }
}
