package com.example.messagingapp.chat

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    private val _chatState = MutableLiveData<ChatState>()
    val chatState: LiveData<ChatState> = _chatState
    private var activeChat: String? = null

    fun setActiveChat(contactView: TextView) {
        with (contactView.text as String) {
            if (this != activeChat) {
                activeChat = this
            }
            _chatState.postValue(ChatState.ContactSelected(contactName = this))
        }
    }

}
