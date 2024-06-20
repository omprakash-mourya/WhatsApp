package com.opdroid.whatsapp.data

import com.opdroid.whatsapp.R

data class ChatListData (
    val chatId: Int,
    val chatName: String,
    val chatImage: Int = R.drawable.account_circle,
    val chatStatus: ChatStatus = ChatStatus.OFFLINE,
    val chatType: ChatType = ChatType.INDIVIDUAL,
    val lastMessage: Message,
    val messageDeliveryStatus: MessageDeliveryStatus = MessageDeliveryStatus.DELIVERED,
    val messageType: MessageType = MessageType.TEXT,
    val messageStatus: MessageStatus = MessageStatus.RECEIVED,
    var unreadCount: Int = 0,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false,
    val isArchived: Boolean = false,
    val isMarkedUnread: Boolean = false
)

data class Message(
    val messageId : Int = 0,
    var message: String,
    val time: String,
    val date: String = "27/05/2024",
    val secondPersonId: Int = 0,
    val messageType: MessageType = MessageType.TEXT,
    val messageDeliveryStatus: MessageDeliveryStatus = MessageDeliveryStatus.DELIVERED,
    val messageStatus: MessageStatus = MessageStatus.RECEIVED,
    val messageDay: MessageDay = MessageDay.DATE
)

enum class MessageDay {
    NOW,
    TODAY,
    YESTERDAY,
    DATE
}

enum class MessageDeliveryStatus {
    PENDING,
    DELIVERED,
    SENT,
    SEEN,
    FAILED
}

enum class MessageType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    LOCATION,
    CONTACT,
    STICKER,
    GIF,
    VOICECALLMADE,
    VOICECALLRECIEVED,
    VIDEOCALLMADE,
    VIDEOCALLRECIEVED,
    POLL,
    REACTION
}

enum class ChatType {
    INDIVIDUAL,
    GROUP
}

enum class ChatStatus {
    ONLINE,
    OFFLINE,
    TYPING,
    RECORDING
}

enum class MessageStatus {
    RECEIVED,
    SENT
}