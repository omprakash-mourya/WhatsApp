package com.opdroid.whatsapp.firebaseproject.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun Context.showMsg(
    msg:String,
    duration:Int = Toast.LENGTH_SHORT
){
    CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(this@showMsg, msg, duration).show()
    }
}

//= Toast.makeText(this,msg,duration).show()