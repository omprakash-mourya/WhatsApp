package com.opdroid.whatsapp.display.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collect
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opdroid.whatsapp.data.INITIAL_HOME_SCREEN_INDEX
import com.opdroid.whatsapp.data.bottomTabs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomTabComponent(initialIndex: Int = 0,
                       pagerState: PagerState,
                       onTabSelected: (Int,Boolean) -> Unit
){

    var selectedTabIndex by remember {
        mutableIntStateOf(initialIndex)
    }

    var isScrolled:Boolean by remember {
        mutableStateOf(true)
    }

            selectedTabIndex = pagerState.currentPage

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .background(
                MaterialTheme.colorScheme.primary
            )
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomTabs.forEachIndexed { index, bottomTab ->
            LazyRow {
                item {
                    BottomBarItems(
                        icon1 = bottomTab.icon,
                        title = bottomTab.title,
                        isClicked = (selectedTabIndex == index),
                        haveNotification = bottomTab.haveNotification,
                        onClick = {
                            selectedTabIndex = index
                            isScrolled = false
                            onTabSelected(selectedTabIndex,isScrolled)
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun BottomBarItems(
    icon1: Int,
    icon2: Int = icon1,
    title:String,
    isClicked: Boolean = false,
    haveNotification: Boolean = false,
    onClick : () -> Unit = {}
) {
//    val primaryColor: Int = MaterialTheme.colorScheme.primary.toArgb()
    val cardWidth by animateDpAsState(if (isClicked) 60.dp else 0.dp, label = "")
    val interactionSource = remember { MutableInteractionSource() }
    var dotColor by remember { mutableStateOf(Color.Transparent) }
    dotColor = if(haveNotification && !isClicked) MaterialTheme.colorScheme.tertiary else Color.Transparent
    Column(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .padding(8.dp)
            .height(60.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Remove the ripple effect
                onClick = onClick
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .height(40.dp)
                .width(62.dp)
        ) {
            Icon(
                painter = painterResource(id = if (isClicked) icon2 else icon1),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp)
            )
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .width(54.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Canvas(modifier = Modifier.size(18.dp)) {
                    drawCircle(
                        color = dotColor,
                        radius = 10f
                    )
                }
            }
            Card(
                modifier = Modifier
                    .height(36.dp)
                    .width(cardWidth)
                    .alpha(0.2f)
                    .align(Alignment.BottomCenter),
                colors = CardDefaults.cardColors(
                    containerColor = if (isClicked) MaterialTheme.colorScheme.tertiary else Color.Transparent
                ),
                shape = RoundedCornerShape(18.dp)
            ) {}
        }
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
        Text(
            text = title,
            fontSize = 14.sp,
            softWrap = false, // Truncate the text with an ellipsis when it overflows the box
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = if (isClicked) FontWeight.SemiBold else FontWeight.Normal
        )
    }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun BottomTabComponentPreview() {
    val pagerState = rememberPagerState(
        initialPage = INITIAL_HOME_SCREEN_INDEX,
        initialPageOffsetFraction = 0f,
        pageCount = { bottomTabs.size }
    )

    BottomTabComponent(INITIAL_HOME_SCREEN_INDEX, pagerState) { _, _ -> }
}