package com.opdroid.whatsapp.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opdroid.whatsapp.R
import com.opdroid.whatsapp.data.ChannelMessageType
import com.opdroid.whatsapp.data.FollowedChannelData
import com.opdroid.whatsapp.data.MessageDay
import com.opdroid.whatsapp.data.MessageDeliveryStatus
import com.opdroid.whatsapp.data.MessageStatus
import com.opdroid.whatsapp.data.MyContact
import com.opdroid.whatsapp.data.UnFollowedChannelData
import com.opdroid.whatsapp.display.components.UserImage
import com.opdroid.whatsapp.dummydata.dummyContactItems
import com.opdroid.whatsapp.dummydata.dummyFollowedChannels
import com.opdroid.whatsapp.dummydata.dummyUnFollowedChannels
import com.opdroid.whatsapp.theme.ThemeManager

@Composable
fun StatusScreen(){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item{ Row(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                 .wrapContentHeight(Alignment.Top),
             horizontalArrangement = Arrangement.SpaceBetween,
             verticalAlignment = Alignment.Top
         ) {
             Text(text = stringResource(id = R.string.status),
                 style = TextStyle(color = MaterialTheme.colorScheme.onPrimary,
                     fontSize = 18.sp,
                     fontWeight = FontWeight.SemiBold
                 ),
                 modifier = Modifier.weight(1f)
             )
             Icon(painter = painterResource(id = R.drawable.menu),
                 contentDescription = "Status settings",
                 tint = MaterialTheme.colorScheme.onPrimary,
                 modifier = Modifier.size(24.dp)
             )
         }
         }
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyRow() {
                    items(dummyContactItems) { item ->
                        StatusItem(myContact = item)
                    }
                }
            }
        }
        item{
             Divider(
                 modifier = Modifier
                     .fillMaxWidth(),
                 color = MaterialTheme.colorScheme.onBackground,
                 thickness = 1.dp
             )
         }
        item{ Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.channels),
                style = TextStyle(color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(painter = painterResource(id = R.drawable.add),
                contentDescription = "Add channel",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
        }
        items(dummyFollowedChannels){item ->
                    FollowedChannelListItem(followedChannel = item,
                        onChannelClick = {channelId ->
                            // TODO Navigate to the channel screen
                        }
                    )
                }
        item{ Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.find_channels),
                style = TextStyle(color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.weight(1f)
            )
            Text(text = stringResource(id = R.string.see_all),
                style = TextStyle(color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                contentDescription = "See all",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(20.dp)
            )
        } }
        item{ Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .wrapContentHeight(Alignment.Top),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow {
                items(dummyUnFollowedChannels){item->
                    UnFollowedChannelListItem(channel = item)
                }
            }
        } }
        item{
            Button(onClick = {
            ThemeManager.toggleTheme()
        }) {
            Text(text = "Change Theme")
        } }
        item{
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


@Composable
fun StatusItem(myContact: MyContact){
    if(myContact.status.isEmpty()) {
        return
    }
    val totalStatuses = myContact.status.size
    val unseenStatuses = myContact.status.count { !it.isStatusSeenByMe }
    val gapAngle = 8f // adjust this value to increase or decrease the gap between arcs
    val sweepAnglePerStatus = 360f / totalStatuses - gapAngle
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier.size(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center)) {
                // Draw individual arcs for each status
                if(totalStatuses==1){
                    drawArc(
                        color = if(unseenStatuses==1) Color.Green else Color.Gray,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                else {
                    for (i in 0 until totalStatuses) {
                        val isSeen = i >= unseenStatuses
                        val color = if (isSeen) Color.Gray else Color.Green
                        val startAngle =
                            -90f + sweepAnglePerStatus * i + gapAngle * i + gapAngle / 2
                        val sweepAngle = sweepAnglePerStatus

                        drawArc(
                            color = color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                }
            }
            // Draw the profile picture
            Image(painter = painterResource(id = myContact.contactImage),
                contentDescription = myContact.mySavedName,
                modifier = Modifier
                    .size(62.dp)
                    .align(Alignment.Center)
            )
        }
        Text(text = myContact.mySavedName,
            style = TextStyle(color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun FollowedChannelListItem(
    followedChannel: FollowedChannelData,
    onChannelClick: (Int) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onChannelClick(followedChannel.channelId) },
        verticalAlignment = Alignment.Top
    ) {
        ChannelImage(followedChannel.channelImage)
        ChannelDetails(followedChannel)
    }
}

@Composable
fun ChannelImage(channelImage: Int) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .width(60.dp)
            .padding(end = 4.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        UserImage(image = channelImage,
            size = 60.dp
        )
    }
}

@Composable
fun ChannelDetails(followedChannel: FollowedChannelData) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ChannelNameAndTime(followedChannel)
        ChannelMessageAndUnreadCount(followedChannel)
    }
}


@Composable
fun ChannelNameAndTime(followedChannel: FollowedChannelData) {
    Row(
        modifier = Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = followedChannel.channelName,
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
                when(followedChannel.lastMessage.messageDay){
                    MessageDay.NOW -> "Now"
                    MessageDay.TODAY -> followedChannel.lastMessage.time
                    MessageDay.YESTERDAY -> "Yesterday"
                    MessageDay.DATE -> followedChannel.lastMessage.date
                }
                ),
            modifier = Modifier.wrapContentWidth(align = Alignment.End),
            fontSize = 12.sp,
            color = if(followedChannel.unreadCount >0) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun ChannelMessageAndUnreadCount(followedChannel: FollowedChannelData) {
    Row(
        modifier = Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.Start)
                .wrapContentHeight(Alignment.CenterVertically)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (followedChannel.lastMessage.messageStatus == MessageStatus.SENT) {
                Icon(
                    painter = painterResource(
                        id =
                        when (followedChannel.lastMessage.messageDeliveryStatus) {
                            MessageDeliveryStatus.PENDING -> R.drawable.timer
                            MessageDeliveryStatus.FAILED -> R.drawable.baseline_report_problem_24
                            MessageDeliveryStatus.DELIVERED -> R.drawable.baseline_done_all_24
                            MessageDeliveryStatus.SENT -> R.drawable.baseline_done_24
                            MessageDeliveryStatus.SEEN -> R.drawable.baseline_done_all_24
                        }
                    ),
                    contentDescription = "",
                    tint = if (followedChannel.lastMessage.messageDeliveryStatus == MessageDeliveryStatus.FAILED) MaterialTheme.colorScheme.error
                    else if (followedChannel.lastMessage.messageDeliveryStatus == MessageDeliveryStatus.SEEN) MaterialTheme.colorScheme.onTertiary
                    else MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(2.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            if (followedChannel.lastMessage.messageType != ChannelMessageType.TEXT
            ) {
                Icon(
                    painter = painterResource(
                        id =
                        when (followedChannel.lastMessage.messageType) {
                            ChannelMessageType.IMAGE -> R.drawable.baseline_photo_24
                            ChannelMessageType.GIF -> R.drawable.baseline_gif_24
                            ChannelMessageType.POLL -> R.drawable.baseline_poll_24
                            ChannelMessageType.TEXT -> R.drawable.baseline_help_outline_24
                            ChannelMessageType.AUDIO -> R.drawable.baseline_keyboard_voice_24
                            ChannelMessageType.VIDEO -> R.drawable.baseline_videocam_24
                            ChannelMessageType.CONTACT -> R.drawable.baseline_person_24
                            ChannelMessageType.STICKER -> R.drawable.baseline_help_outline_24
                            ChannelMessageType.LOCATION -> R.drawable.baseline_location_on_24
                            ChannelMessageType.DOCUMENT -> R.drawable.baseline_folder_open_24
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
                text = followedChannel.lastMessage.message,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp
            )
        }

        if(followedChannel.unreadCount > 0){
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                if (followedChannel.unreadCount > 0) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(shape = androidx.compose.foundation.shape.CircleShape)
                            .background(color = MaterialTheme.colorScheme.tertiary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = followedChannel.unreadCount.toString(),
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UnFollowedChannelListItem(
    channel: UnFollowedChannelData
){
    var buttonName by remember {
        mutableStateOf("Follow")
    }
    Card(
        modifier = Modifier
            .wrapContentHeight(Alignment.Top)
            .width(100.dp)
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(20.dp)

    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UnFollowedChannelImage(channel.channelImage)
            UnFollowedChannelDetails(channel,
                buttonName
            ) {
                buttonName = if (buttonName == "Follow") "Following"
                else "Follow"
            }
        }
    }
}

@Composable
fun UnFollowedChannelImage(channelImage: Int) {
    Box(modifier = Modifier
        .size(76.dp),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(channelImage),
            contentDescription = "",
            modifier = Modifier
                .size(76.dp)
                .clip(shape = androidx.compose.foundation.shape.CircleShape)
                .background(color = MaterialTheme.colorScheme.primary)
        )
        Box(
            modifier = Modifier
                .size(76.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(shape = androidx.compose.foundation.shape.CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_verified_24),
                    contentDescription = "Verified",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@Composable
fun UnFollowedChannelDetails(channel: UnFollowedChannelData,
                             buttonName:String,
                             onThemeButtonClick:() -> Unit ={} ) {
    Text(text = channel.channelName,
        style = TextStyle(color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
    )
    Button(onClick = {
                     onThemeButtonClick()
        /*TODO set follow function*/
    },
        modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
    ) {
        Text(text = buttonName,
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview (showBackground = true)
@Composable
fun StatusScreenPreview(){
    StatusScreen()
}