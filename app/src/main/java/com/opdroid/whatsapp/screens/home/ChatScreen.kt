package com.opdroid.whatsapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opdroid.whatsapp.R
import com.opdroid.whatsapp.data.ChatListData
import com.opdroid.whatsapp.data.MessageDay
import com.opdroid.whatsapp.data.MessageDeliveryStatus
import com.opdroid.whatsapp.data.MessageStatus
import com.opdroid.whatsapp.data.MessageType
import com.opdroid.whatsapp.dummydata.dummyChatListItems
import com.opdroid.whatsapp.display.components.UserImage


@Composable
fun ChatScreen(
    onChatClick: (Int) -> Unit = {}
){
    Box(modifier = Modifier.fillMaxSize() ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 10.dp)
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top
        ) {
            items(dummyChatListItems){ chatData ->
                ChatListItem(chatData,onChatClick,onChatClick)
            }
        }

        FloatingActionButton(
            onClick = { /* Handle FAB click here */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(painter = painterResource(id = R.drawable.baseline_person_add_alt_1_24), contentDescription = "Add")
        }
    }
    
}

@Composable
fun ChatListItem(chatListItemData: ChatListData,
                 onImageClick: (Int) -> Unit = {},
                 onChatClick: (Int) -> Unit = {}
                 ){
    if(chatListItemData.isArchived) return
    if(chatListItemData.lastMessage.messageStatus == MessageStatus.SENT){
        chatListItemData.unreadCount = 0
    }
    chatListItemData.lastMessage.message =  when(chatListItemData.lastMessage.messageType){
        MessageType.TEXT -> chatListItemData.lastMessage.message
        MessageType.IMAGE -> "Image"
        MessageType.GIF -> "GIF"
        MessageType.POLL -> "Poll"
        MessageType.AUDIO -> "Audio"
        MessageType.VIDEO -> "Video"
        MessageType.CONTACT -> "Contact"
        MessageType.STICKER -> "Sticker"
        MessageType.LOCATION -> "Location"
        MessageType.DOCUMENT -> "Document"
        MessageType.REACTION -> "Reaction"
        MessageType.VIDEOCALLMADE -> "Video call"
        MessageType.VIDEOCALLRECIEVED -> "Video call"
        MessageType.VOICECALLMADE -> "Voice call"
        MessageType.VOICECALLRECIEVED -> "Voice call"
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onChatClick(chatListItemData.chatId) },
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .padding(end = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            UserImage(image = chatListItemData.chatImage,
                size = 60.dp,
                onClick = {onImageClick(chatListItemData.chatId)}
            )
        }
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = chatListItemData.chatName,
                    softWrap = false,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onPrimary,
                    // Truncate the text with an ellipsis when it overflows the box
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                    )
                Text(text = (
                        when(chatListItemData.lastMessage.messageDay){
                            MessageDay.NOW -> "Now"
                            MessageDay.TODAY -> chatListItemData.lastMessage.time
                            MessageDay.YESTERDAY -> "Yesterday"
                            MessageDay.DATE -> chatListItemData.lastMessage.date
                        }
                        ),
                    modifier = Modifier.wrapContentWidth(align = Alignment.End),
                    fontSize = 12.sp,
                    color = if(chatListItemData.unreadCount >0) MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.onSecondary
                )
            }
            Row(
                modifier = Modifier.wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.Start)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                if (chatListItemData.lastMessage.messageStatus == MessageStatus.SENT) {
                    Icon(
                        painter = painterResource(
                            id =
                            when (chatListItemData.lastMessage.messageDeliveryStatus) {
                                MessageDeliveryStatus.PENDING -> R.drawable.timer
                                MessageDeliveryStatus.FAILED -> R.drawable.baseline_report_problem_24
                                MessageDeliveryStatus.DELIVERED -> R.drawable.baseline_done_all_24
                                MessageDeliveryStatus.SENT -> R.drawable.baseline_done_24
                                MessageDeliveryStatus.SEEN -> R.drawable.baseline_done_all_24
                            }
                        ),
                        contentDescription = "",
                        tint = if (chatListItemData.lastMessage.messageDeliveryStatus == MessageDeliveryStatus.FAILED) MaterialTheme.colorScheme.error
                        else if (chatListItemData.lastMessage.messageDeliveryStatus == MessageDeliveryStatus.SEEN) MaterialTheme.colorScheme.onTertiary
                        else MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(2.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                if (chatListItemData.lastMessage.messageType != MessageType.TEXT &&
                    chatListItemData.lastMessage.messageType != MessageType.REACTION
                ) {
                    Icon(
                        painter = painterResource(
                            id =
                            when (chatListItemData.lastMessage.messageType) {
                                MessageType.VIDEOCALLMADE -> R.drawable.baseline_video_call_24
                                MessageType.VIDEOCALLRECIEVED -> R.drawable.baseline_video_call_24
                                MessageType.VOICECALLMADE -> R.drawable.call
                                MessageType.VOICECALLRECIEVED -> R.drawable.baseline_phone_callback_24
                                MessageType.IMAGE -> R.drawable.baseline_photo_24
                                MessageType.GIF -> R.drawable.baseline_gif_24
                                MessageType.POLL -> R.drawable.baseline_poll_24
                                MessageType.TEXT -> R.drawable.baseline_help_outline_24
                                MessageType.AUDIO -> R.drawable.baseline_keyboard_voice_24
                                MessageType.VIDEO -> R.drawable.baseline_videocam_24
                                MessageType.CONTACT -> R.drawable.baseline_person_24
                                MessageType.STICKER -> R.drawable.baseline_help_outline_24
                                MessageType.LOCATION -> R.drawable.baseline_location_on_24
                                MessageType.REACTION -> R.drawable.baseline_help_outline_24
                                MessageType.DOCUMENT -> R.drawable.baseline_folder_open_24
                            }
                        ),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(2.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = chatListItemData.lastMessage.message,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp
                )
            }

                if(chatListItemData.unreadCount > 0 || chatListItemData.isPinned || chatListItemData.isMuted){

                    Row(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        if (chatListItemData.isMuted) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_notifications_off_24),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(2.dp)
                            )
                        }
                        if (chatListItemData.isPinned) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_push_pin_24),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(2.dp)
                            )
                        }
                        if (chatListItemData.unreadCount > 0) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(shape = androidx.compose.foundation.shape.CircleShape)
                                    .background(color = MaterialTheme.colorScheme.tertiary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = chatListItemData.unreadCount.toString(),
                                    color = Color.White,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }

    }

}

@Preview (showBackground = true)
@Composable
fun ChatScreenPreview(){
    ChatScreen()
}