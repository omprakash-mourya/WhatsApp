package com.opdroid.whatsapp.display.components

import android.content.ClipDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UserImage(image: Int,
              contentDescription: String = "",
              size: Dp = 50.dp,
              onClick: () -> Unit = {}
) {
    // User Image
    Image(
        painter = painterResource(id = image),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .clickable { onClick() }
    )
}