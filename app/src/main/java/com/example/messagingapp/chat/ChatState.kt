package com.example.messagingapp.chat

open class ChatState() {
    data class ContactSelected(val contactName: String) : ChatState()

}
