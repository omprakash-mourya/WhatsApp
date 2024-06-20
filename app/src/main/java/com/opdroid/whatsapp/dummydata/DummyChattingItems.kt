package com.opdroid.whatsapp.dummydata

import com.opdroid.whatsapp.data.MyContact
import com.opdroid.whatsapp.data.Message
import com.opdroid.whatsapp.data.MessageDeliveryStatus
import com.opdroid.whatsapp.data.MessageStatus

val dummyChattingItems = listOf(
    MyContact(
        contactId = 1,
        contactName = "John Doe",
        mySavedName = "John",
        unreadCount = 2,
        lastSeen = "1 hour ago",
        isOnline = true,
        isTyping = true
    ),
    MyContact(
        contactId = 2,
        contactName = "Jane Doe",
        mySavedName = "Jane",
        unreadCount = 1,
        lastSeen = "1 hour ago",
        isOnline = false,
        isTyping = false
    )
)

val dummyMessage= listOf(
    Message(
        message = "Hey there! I am using WhatsApp.",
        time = "1 hour ago"
    ),
    Message(
        message = "Hey there! I am using WhatsApp.",
        time = "1 hour ago",
        messageStatus = MessageStatus.SENT
    ),
    Message(
        message = "Hey there! I am using WhatsApp.",
        time = "1 hour ago"
    ),
    Message(
        message = "Hey there! I am using WhatsApp.",
        time = "1 hour ago",
        messageStatus = MessageStatus.SENT,
        messageDeliveryStatus = MessageDeliveryStatus.SEEN
    )
)