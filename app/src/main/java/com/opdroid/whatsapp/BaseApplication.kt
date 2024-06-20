package com.opdroid.whatsapp

import android.app.Application
import com.opdroid.whatsapp.theme.PreferencesManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){
    companion object {
        lateinit var preferencesManager: PreferencesManager
            private set
    }

    override fun onCreate() {
        super.onCreate()
        preferencesManager = PreferencesManager(applicationContext)
    }
}