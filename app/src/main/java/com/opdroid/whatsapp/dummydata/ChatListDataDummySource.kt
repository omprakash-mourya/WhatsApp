package com.opdroid.whatsapp.dummydata

import com.opdroid.whatsapp.R
import com.opdroid.whatsapp.data.ChatListData
import com.opdroid.whatsapp.data.ChatStatus
import com.opdroid.whatsapp.data.ChatType
import com.opdroid.whatsapp.data.Message
import com.opdroid.whatsapp.data.MessageDeliveryStatus
import com.opdroid.whatsapp.data.MessageStatus
import com.opdroid.whatsapp.data.MessageType

val dummyChatListItems = listOf<ChatListData>(
    ChatListData(
        chatId = 1,
        chatName = "Ghar",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "10:43 AM"
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),
    ChatListData(
        chatId = 2,
        chatName = "Putin",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "7:53 AM",
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.TYPING,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),
    ChatListData(
        chatId = 3,
        chatName = "John Dorsy",
        lastMessage = Message(
            message = "can you please send me the document?",
            time = "6:50 PM"
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.RECORDING,
        chatImage = R.drawable.account_circle,
        unreadCount = 0,
        isMuted = true,
        isPinned = true
    ),
    ChatListData(
        chatId = 4,
        chatName = "mELON MUSK",
        lastMessage = Message(
            message = "Let's meet tomorrow at the office.",
            time = "7:13 PM",
            messageStatus = MessageStatus.SENT,
            messageDeliveryStatus = MessageDeliveryStatus.SEEN
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.ONLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 0,
        isMuted = true
    ),
    ChatListData(
        chatId = 5,
        chatName = "Jeff Bezos",
        lastMessage = Message(
            message = "Want to buy your app.",
            time = "4:53 PM",
            messageType = MessageType.VIDEO,
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.TYPING,
        chatImage = R.drawable.account_circle,
        unreadCount = 0,
        isMuted = true,
        isPinned = true
    ),
    ChatListData(
        chatId = 6,
        chatName = "Dhruv Rathi",
        lastMessage = Message(
            message = "Sponsorship for the next video?",
            time = "12:40 PM",
            messageType = MessageType.VOICECALLRECIEVED,
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        unreadCount = 3,
        isMuted = true,
        isPinned = true
    ),
    ChatListData(
        chatId = 7,
        chatName = "Sundar Pichai",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "10:43 AM",
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),
    ChatListData(
        chatId = 8,
        chatName = "Mark Zuckerberg",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "10:43 AM",
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),
    ChatListData(
        chatId = 9,
        chatName = "Bill Gates",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "10:43 AM",
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),
    ChatListData(
        chatId = 10,
        chatName = "Elon Musk",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "10:43 AM",
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),
    ChatListData(
        chatId = 11,
        chatName = "Tim Cook",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "10:43 AM",
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),
    ChatListData(
        chatId = 12,
        chatName = "Satya Nadella",
        lastMessage = Message(
            message = "Hey, how are you?",
            time = "10:43 AM",
            messageStatus = MessageStatus.RECEIVED
        ),
        chatType = ChatType.INDIVIDUAL,
        chatStatus = ChatStatus.OFFLINE,
        chatImage = R.drawable.account_circle,
        unreadCount = 1
    ),

)
