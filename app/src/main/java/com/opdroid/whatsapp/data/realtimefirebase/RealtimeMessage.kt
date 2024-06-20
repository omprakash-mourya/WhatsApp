package com.opdroid.whatsapp.data.realtimefirebase

data class RealtimeMessage(
    val messageId: String,
    val senderId: String,
    val content: String,
    val type: String,
    val timestamp: Long,
    val isDelivered: Boolean
)