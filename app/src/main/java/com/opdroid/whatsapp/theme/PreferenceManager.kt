package com.opdroid.whatsapp.theme

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun setDarkThemeEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("dark_theme_enabled", enabled).apply()
    }

    fun isDarkThemeEnabled(): Boolean {
        return sharedPreferences.getBoolean("dark_theme_enabled", false)
    }

    fun setLoggedIn(value: Boolean) {
        sharedPreferences.edit().putBoolean("logged_in", value).apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("logged_in", false)
    }

    fun setOwnerCountryCode(countryCode:String? ="+91"){
        sharedPreferences.edit().putString("owner_country_code", countryCode).apply()
    }

    fun setOwnerContactNumber(phone: Long) {
        sharedPreferences.edit().putLong("owner_contact", phone).apply()
    }

    fun getOwnerContactNumber(): Long {
        return sharedPreferences.getLong("owner_contact", 0L)
    }

    fun getOwnerCountryCode(): String {
        return sharedPreferences.getString("owner_country_code", "+91") ?: "+91"
    }

    fun setUserName(name: String) {
        sharedPreferences.edit().putString("owner_name", name).apply()
    }

    fun getUserName(): String {
        return sharedPreferences.getString("owner_name", "User") ?: ""
    }

    fun setUserUid(uid: String) {
        sharedPreferences.edit().putString("owner_uid", uid).apply()
    }

    fun getUserUid(): String {
        return sharedPreferences.getString("owner_uid", "") ?: ""
    }

}
