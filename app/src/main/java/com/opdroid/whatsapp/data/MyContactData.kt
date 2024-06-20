package com.opdroid.whatsapp.data

import com.opdroid.whatsapp.R

data class MyContact(
    var contactId: Int = 0,
    var contactName: String = "",
    var mySavedName: String = "",
    var phoneNumber: Long = 0,
    var contactImage: Int = R.drawable.account_circle,
    var status: ArrayList<Status> = ArrayList(),
    var unreadCount: Int = 0,
    var lastSeen: String = "",
    var isOnline: Boolean = true,
    var isTyping: Boolean = false,
    var isMuted: Boolean = false,
    var isPinned: Boolean = false,
    var isArchived: Boolean = false,
    var isMarkedUnread: Boolean = false,
    var isInFirebase: Boolean = false
)

data class Status (
    val statusType: StatusType = StatusType.IMAGE,
    val text: String = "",
    val statusTime: String,
    val isStatusSeenByMe: Boolean = false
)

data class MyStatus(
    val statusType: StatusType = StatusType.IMAGE,
    val text: String = "",
    val statusTime: String,
    val statusSeenBy : ArrayList<MyContact> = ArrayList()
)

enum class StatusType {
    IMAGE,
    VIDEO
}

