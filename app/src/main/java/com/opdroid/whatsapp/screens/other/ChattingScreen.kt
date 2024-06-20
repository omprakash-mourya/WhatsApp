package com.opdroid.whatsapp.screens.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opdroid.whatsapp.R
import com.opdroid.whatsapp.data.Message
import com.opdroid.whatsapp.data.MessageDeliveryStatus
import com.opdroid.whatsapp.data.MessageStatus
import com.opdroid.whatsapp.data.MyContact

@Composable
fun ChattingScreen(
    myContact: MyContact,
    messages: List<Message>
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()  // Add padding for the status bar
            .navigationBarsPadding()  // Add padding for the navigation bar
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        ChattingScreenHeaderComponent(myContact = myContact)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .weight(1f)
        ) {
            items(messages.size){
                ChatMessageComponent(messages[it])
            }
        }
        BottomChatComponent()
    }
}

@Composable
fun ChattingScreenHeaderComponent(
    myContact: MyContact,
    isThisGroup: Boolean = false
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .wrapContentSize(Alignment.CenterStart)
                .background(color = MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.onPrimary)
            Image(painter = painterResource(myContact.contactImage), contentDescription = myContact.contactName +"'s profile",
                modifier = Modifier
                    .size(56.dp)
                    .clip(shape = CircleShape)
            )
            Column(
                modifier = Modifier
                    .wrapContentSize(Alignment.CenterStart)
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = myContact.contactName, fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = if (myContact.isOnline) "online"
                else if(myContact.isOnline && myContact.isTyping) "Typing..."
                else "last seen at " + myContact.lastSeen,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.videocam), contentDescription = "Video call", tint = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.width(20.dp))
        Icon(painter = painterResource(id = R.drawable.add_call), contentDescription = "Call", tint = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.width(20.dp))
        Icon(painter = painterResource(id = R.drawable.menu), contentDescription = "More", tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun BottomChatComponent(){
    var textFieldValue by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically)
                .background(color = Color.Transparent),
            shape = RoundedCornerShape(size = 40.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically)
                    .background(color = MaterialTheme.colorScheme.onBackground)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_emoji_emotions_24),
                        contentDescription = "emoji",
                        tint = MaterialTheme.colorScheme.onSecondary,
                    )
                    TextField(value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        placeholder = { Text(text = "Message") },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colorScheme.onBackground,
                            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                            textColor = MaterialTheme.colorScheme.onPrimary,
                            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                            cursorColor = MaterialTheme.colorScheme.onSecondary,
                            errorCursorColor = MaterialTheme.colorScheme.onSecondary,
                            errorIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                            disabledIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                            leadingIconColor = MaterialTheme.colorScheme.onSecondary,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
                            errorLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
                            trailingIconColor = MaterialTheme.colorScheme.onSecondary,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSecondary,
                            errorTrailingIconColor = MaterialTheme.colorScheme.onSecondary,
                            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                            disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                            errorLabelColor = MaterialTheme.colorScheme.onSecondary,
                            placeholderColor = MaterialTheme.colorScheme.onSecondary,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSecondary
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .background(color = MaterialTheme.colorScheme.secondary)
                    )
                    Icon(painter = painterResource(id = R.drawable.baseline_attach_file_24),
                        contentDescription = "attach",
                        tint = MaterialTheme.colorScheme.onSecondary,
                    )
                    if(textFieldValue.isEmpty()){
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(painter = painterResource(id = R.drawable.baseline_monetization_on_24),
                            contentDescription = "emoji",
                            tint = MaterialTheme.colorScheme.onSecondary,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(painter = painterResource(id = R.drawable.camera_filled),
                            contentDescription = "emoji",
                            tint = MaterialTheme.colorScheme.onSecondary,
                        )

                    }


                }
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center
        ){
            Icon(painter = painterResource(id = if(textFieldValue.isEmpty()) R.drawable.baseline_mic_24 else R.drawable.baseline_send_24),
                contentDescription = "record and send",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
                )
        }
    }
}

@Composable
fun ChatMessageComponent(
    message: Message
){
    val isSentByMe = message.messageStatus == MessageStatus.SENT
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.Transparent)
            .padding(16.dp),
        horizontalArrangement = if(isSentByMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Transparent),
            shape = RoundedCornerShape(size = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .background(color = if (isSentByMe) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onBackground),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(8.dp)
                        .background(color = Color.Transparent)
                        .width(280.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text(text = message.message,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = message.time,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(8.dp)
                    )
                    if (message.messageStatus== MessageStatus.SENT){
                        Icon(painter = painterResource(id =
                        if (message.messageDeliveryStatus == MessageDeliveryStatus.SENT) R.drawable.baseline_done_24
                        else if (message.messageDeliveryStatus == MessageDeliveryStatus.PENDING) R.drawable.timer
                        else if (message.messageDeliveryStatus == MessageDeliveryStatus.FAILED) R.drawable.baseline_error_24
                        else R.drawable.baseline_done_all_24),
                            contentDescription = "message status",
                            tint = if(message.messageDeliveryStatus == MessageDeliveryStatus.SEEN) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}