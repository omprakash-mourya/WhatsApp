package com.opdroid.whatsapp.theme

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeManager {
    private lateinit var preferencesManager: PreferencesManager
    var isDarkTheme by mutableStateOf(false)
        private set

    fun initialize(context: Context) {
        preferencesManager = PreferencesManager(context)
        isDarkTheme = preferencesManager.isDarkThemeEnabled()
    }

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
        preferencesManager.setDarkThemeEnabled(isDarkTheme)
    }
}