package com.opdroid.whatsapp.extra

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ProgressDialogState {
    var isDisplayed by mutableStateOf(false)
    var text by mutableStateOf<String?>(null)
}

fun showProgressDialog(customText: String? = null) {
    ProgressDialogState.isDisplayed = true
    ProgressDialogState.text = customText
}

fun hideProgressDialog() {
    ProgressDialogState.isDisplayed = false
    ProgressDialogState.text = null
}