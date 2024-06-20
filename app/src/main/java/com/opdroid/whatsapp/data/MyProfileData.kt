package com.opdroid.whatsapp.data

import com.opdroid.whatsapp.R

data class MyProfileData(
    var userName: String,
    val mobileNumber: String,
    var profileImage: Int = R.drawable.account_circle,
    val status: String,
    var isOnline: Boolean,
    var isTyping: Boolean,
    var contactList: List<MyContact> = emptyList()
)