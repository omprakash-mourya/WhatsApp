package com.opdroid.whatsapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opdroid.whatsapp.data.INITIAL_HOME_SCREEN_INDEX
import com.opdroid.whatsapp.data.bottomTabs
import com.opdroid.whatsapp.display.components.AppBarComponent
import com.opdroid.whatsapp.display.components.BottomTabComponent
import com.opdroid.whatsapp.screens.home.CallScreen
import com.opdroid.whatsapp.screens.home.ChatScreen
import com.opdroid.whatsapp.screens.home.CommunitiesScreen
import com.opdroid.whatsapp.screens.home.StatusScreen
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(isDarkTheme: Boolean,
               onChatClick:(Int) -> Unit = {}
               ) {
    val pagerState = rememberPagerState(
        initialPage = INITIAL_HOME_SCREEN_INDEX,
        initialPageOffsetFraction = 0f,
        pageCount = { bottomTabs.size }
    )
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()  // Add padding for the status bar
            .navigationBarsPadding()  // Add padding for the navigation bar
            .background(color = MaterialTheme.colorScheme.background)
    ){
        AppBarComponent(
            pagerState = pagerState,
            isDarkTheme
        )
        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = pagerState
        ) {page ->
            when(page){
                0 -> ChatScreen(onChatClick)
                1 -> StatusScreen()
                2 -> CommunitiesScreen()
                3 -> CallScreen()
            }

        }
        BottomTabComponent(
            INITIAL_HOME_SCREEN_INDEX,
            pagerState = pagerState
        ) { selectedPage, isScrolled ->
            scope.launch {
                //do not animate scrolling on just clicking and not scrolling
                if (abs(pagerState.currentPage - selectedPage) == 1 && isScrolled) {
                    pagerState.animateScrollToPage(selectedPage)
                }else{
                    pagerState.scrollToPage(selectedPage)
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(true)
}