package com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.repository

import android.app.Activity
import com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.model.AuthUser
import com.opdroid.whatsapp.firebaseproject.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>

    fun loginUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>

    fun createUserWithPhone(
        phone:String,
        activity:Activity
    ) : Flow<ResultState<String>>

    fun signWithCredential(
        otp:String
    ): Flow<ResultState<String>>

}