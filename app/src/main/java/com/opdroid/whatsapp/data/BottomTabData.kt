package com.opdroid.whatsapp.data

import com.opdroid.whatsapp.R

data class BottomTabData(
    val icon: Int,
    val title: String,
    val route: String,
    val haveNotification: Boolean = false
)
//
enum class BottomTab(
    val icon: Int,
    val title: String,
    val route: String,
    val haveNotification: Boolean = false
) {
    CHATS(icon = R.drawable.chat, title = "Chats", route = "chats"),
    UPDATES(icon = R.drawable.baseline_updates_24, title = "Updates", route = "updates", haveNotification = true),
    COMMUNITIES(icon = R.drawable.communities, title = "Communities", route = "communities"),
    CALLS(icon = R.drawable.call, title = "Calls", route = "calls")
}

val bottomTabs = listOf(
    BottomTab.CHATS,
    BottomTab.UPDATES,
    BottomTab.COMMUNITIES,
    BottomTab.CALLS
)

const val INITIAL_HOME_SCREEN_INDEX = 0