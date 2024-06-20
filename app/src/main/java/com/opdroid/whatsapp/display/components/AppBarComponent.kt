package com.opdroid.whatsapp.display.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opdroid.whatsapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppBarComponent(
    pagerState: PagerState,
    isDarkTheme: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                MaterialTheme.colorScheme.primary
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = stringResource(id =
        when(pagerState.currentPage){
            0 -> R.string.app_name
            1 -> R.string.updates
            2 -> R.string.communities
            3 -> R.string.calls
            else -> R.string.app_name
        }
        ),
            style = TextStyle(color = if(pagerState.currentPage == 0 && !isDarkTheme) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                fontWeight = if(pagerState.currentPage == 0) androidx.compose.ui.text.font.FontWeight.SemiBold else androidx.compose.ui.text.font.FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24), contentDescription = "Scan and Pay", tint = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.width(20.dp))
        Icon(painter = painterResource(id = R.drawable.camera_filled), contentDescription = "Add Status", tint = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.width(20.dp))
        if(pagerState.currentPage == 1 || pagerState.currentPage == 3){
            Icon(painter = painterResource(id = R.drawable.search), contentDescription = "Search", tint = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.width(20.dp))
        }
        Icon(painter = painterResource(id = R.drawable.menu), contentDescription = "Menu", tint = MaterialTheme.colorScheme.onPrimary)
    }
}