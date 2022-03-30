package com.example.messagingapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.messagingapp.R
import com.example.messagingapp.chat.ChatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    companion object {
        const val USERNAME_EXTRA = "username"
    }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
        setupButtons()
    }

    private fun subscribeObservers() {
        viewModel.loginState.observe(this) { loginState ->
            when (loginState) {
                is LoginDataState.LoginSuccess -> {
                    findViewById<TextView>(R.id.tvLoginError).visibility = View.GONE
                    findViewById<ProgressBar>(R.id.pbLogin).visibility = View.GONE
                    val intent = Intent(this, ChatActivity::class.java).apply {
                        putExtra(USERNAME_EXTRA, loginState.username)
                    }
                    startActivity(intent)
                }
                is LoginDataState.LoginFailed -> {
                    findViewById<TextView>(R.id.tvLoginError).visibility = View.VISIBLE
                    findViewById<ProgressBar>(R.id.pbLogin).visibility = View.GONE
                }
                is LoginDataState.LoggingIn -> findViewById<ProgressBar>(R.id.pbLogin).visibility = View.VISIBLE
            }
        }
    }

    private fun setupButtons() {
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnLogin.setOnClickListener {
            viewModel.tryLogin(findViewById<EditText>(R.id.etUsername), findViewById<EditText>(R.id.etPassword))
        }
        btnRegister.setOnClickListener {
            viewModel.registerUser(findViewById<EditText>(R.id.etUsername), findViewById<EditText>(R.id.etPassword))
        }
    }
}
