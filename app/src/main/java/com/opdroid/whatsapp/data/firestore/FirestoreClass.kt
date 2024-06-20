package com.opdroid.whatsapp.data.firestore

import android.provider.ContactsContract
import com.google.firebase.Timestamp

data class User(
    val uid: String,
    var username: String,
    var email: ContactsContract.CommonDataKinds.Email? = null,
    val phoneNumber: String,
    var profilePicture: Int,
    var bio: String = "Hey there! I am using another WhatsApp made by Omprakash.",
    var status: String? = null
)

data class Chat(
    val chatId: String,
    val participants: List<String>,
    val lastMessage: Message
)

data class Message(
    val senderId: String,
    val content: String,
    val timestamp: Timestamp
)