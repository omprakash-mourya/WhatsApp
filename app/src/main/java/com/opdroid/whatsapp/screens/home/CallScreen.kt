package com.opdroid.whatsapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CallScreen(){
    Column(
        modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Call Screen",
            color = MaterialTheme.colorScheme.onSecondary)
    }
}

@Preview (showBackground = true)
@Composable
fun CallScreenPreview(){
    CallScreen()
}