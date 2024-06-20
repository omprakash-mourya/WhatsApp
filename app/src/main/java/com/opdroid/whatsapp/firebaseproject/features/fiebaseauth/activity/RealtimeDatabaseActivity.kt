package com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.opdroid.whatsapp.ui.theme.WhatsAppTheme
import com.opdroid.whatsapp.firebaseproject.features.firebaseRealtimeDb.ui.RealtimeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RealtimeDatabaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppTheme {
                val isInsert = remember { mutableStateOf(false)}
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                isInsert.value = true
                            }) {
                                Icon(Icons.Default.Add, contentDescription = "")
                            }
                        }
                    ) {
                        val padding = it
                        RealtimeScreen(isInsert)
                    }
                }
            }
        }
    }
}
