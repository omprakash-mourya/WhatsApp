package com.opdroid.whatsapp.dummydata

import com.opdroid.whatsapp.data.ChannelMessage
import com.opdroid.whatsapp.data.FollowedChannelData
import com.opdroid.whatsapp.data.UnFollowedChannelData

val dummyFollowedChannels = listOf(
    FollowedChannelData(
        channelId = 1,
        channelName = "Channel 1",
        lastMessage = ChannelMessage(
            message = "Hey there! I am using WhatsApp.",
            time = "1 hour ago"
        ),
        followers = 1000f
    ),
    FollowedChannelData(
        channelId = 2,
        channelName = "Channel 2",
        lastMessage = ChannelMessage(
            message = "Hey there! I am using WhatsApp.",
            time = "1 hour ago"
        ),
        followers = 2000f
    ),
    FollowedChannelData(
        channelId = 3,
        channelName = "Channel 3",
        lastMessage = ChannelMessage(
            message = "Hey there! I am using WhatsApp.",
            time = "1 hour ago"
        ),
        followers = 3000f
    ),
    FollowedChannelData(
        channelId = 4,
        channelName = "Channel 4",
        lastMessage = ChannelMessage(
            message = "Hey there! I am using WhatsApp.",
            time = "1 hour ago"
        ),
        followers = 4000f
    ),
)

val dummyUnFollowedChannels = listOf(
    UnFollowedChannelData(
        channelId = 3,
        channelName = "Channel 3",
        followers = 3000f
    ),
    UnFollowedChannelData(
        channelId = 4,
        channelName = "Channel 4",
        followers = 4000f
    ),
    UnFollowedChannelData(
        channelId = 5,
        channelName = "Channel 5",
        followers = 5000f
    )
)