package com.example.messagingapp.login

import android.util.Log
import android.widget.TextView
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

    private val _loginState: MutableLiveData<LoginDataState> = MutableLiveData()
    val loginState: LiveData<LoginDataState>
        get() = _loginState


    fun tryLogin(usernameView: TextView, passwordView: TextView) {
        val username = usernameView.text.toString()
        val password = passwordView.text.toString()
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.userExists(username, password)) {
                _loginState.postValue(LoginDataState.LoginSuccess(username))
                Log.d(this::class.simpleName, "Found user!")
            } else {
                _loginState.postValue(LoginDataState.LoginFailed)
                Log.d(this::class.simpleName, "Username or password incorrect")
            }
        }
    }

    fun registerUser(usernameView: TextView, passwordView: TextView) {
        val username = usernameView.text.toString()
        val password = passwordView.text.toString()
        viewModelScope.launch(Dispatchers.IO) {
            repo.registerUser(username, password)
            _loginState.postValue(LoginDataState.LoginSuccess(username))
            Log.d(this::class.simpleName, "User registered!")
        }
    }
}
