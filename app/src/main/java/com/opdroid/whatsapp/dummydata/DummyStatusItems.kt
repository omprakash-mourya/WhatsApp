package com.opdroid.whatsapp.dummydata

import com.opdroid.whatsapp.data.MyContact
import com.opdroid.whatsapp.data.MyStatus
import com.opdroid.whatsapp.data.Status
import com.opdroid.whatsapp.data.StatusType

val dummyContactItems = listOf(
    MyContact(
        contactId = 1,
        contactName = "John Doe",
        mySavedName = "John",
        status = arrayListOf(
            Status(
                statusType = StatusType.IMAGE,
                text = "Hey there! I am using WhatsApp.",
                statusTime = "1 hour ago"
            ),
            Status(
                statusType = StatusType.IMAGE,
                text = "Hey there! I am using WhatsApp.",
                statusTime = "1 hour ago",
                isStatusSeenByMe = true
            )
        )
    ),
    MyContact(
        contactId = 2,
        contactName = "Jane Doe",
        mySavedName = "Jane",
        status = arrayListOf(
            Status(
                statusType = StatusType.IMAGE,
                text = "Hey there! I am using WhatsApp.",
                statusTime = "1 hour ago"
            )
        )
    )
)