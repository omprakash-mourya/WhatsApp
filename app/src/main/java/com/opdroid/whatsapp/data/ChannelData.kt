package com.opdroid.whatsapp.data

import com.opdroid.whatsapp.R

data class FollowedChannelData(
    val channelId: Int,
    val channelName: String = "",
    val channelImage: Int = R.drawable.account_circle,
    var unreadCount: Int = 0,
    val lastMessage: ChannelMessage,
    val followers: Float
)
//
data class ChannelMessage(
    val messageId : Int = 0,
    val message: String,
    val time: String,
    val date: String = "27/05/2024",
    val senderId: Int = 0,
    val messageDay: MessageDay = MessageDay.DATE,
    val messageType: ChannelMessageType = ChannelMessageType.TEXT,
    val messageDeliveryStatus: MessageDeliveryStatus = MessageDeliveryStatus.DELIVERED,
    val messageStatus: MessageStatus = MessageStatus.RECEIVED,
)

data class UnFollowedChannelData(
    val channelId: Int,
    val channelName: String = "",
    val channelImage: Int = R.drawable.account_circle,
    val followers: Float,
    var isVerified:Boolean = true,
    var isFollowing:Boolean = false
)

enum class ChannelMessageType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    LOCATION,
    CONTACT,
    STICKER,
    GIF,
    POLL
}

