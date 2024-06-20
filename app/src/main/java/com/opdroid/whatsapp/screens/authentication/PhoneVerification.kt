package com.opdroid.whatsapp.screens.authentication

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

/*
@Composable
fun PhoneNumberVerificationScreen() {
    var phoneNumber by remember { mutableStateOf("") }

    Column {
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") }
        )
        Button(onClick = { startPhoneNumberVerification(phoneNumber, lifecycleOwner, context) }) {
            Text("Verify")
        }
    }
}

fun startPhoneNumberVerification(phoneNumber: String, lifecycleOwner: LifecycleOwner, context: Context) {
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            signInWithPhoneAuthCredential(lifecycleOwner)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")
        }
    }

    PhoneAuthProvider.getInstance().verifyPhoneNumber(
        phoneNumber, // Phone number to verify
        60, // Timeout duration
        TimeUnit.SECONDS, // Unit of timeout
        context, // Activity (for callback binding)
        callbacks) // OnVerificationStateChangedCallbacks
}

fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, lifecycleOwner: LifecycleOwner) {
    val auth = Firebase.auth
    auth.signInWithCredential(credential)
        .addOnCompleteListener(lifecycleOwner) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success")

                val user = task.result?.user
                // TODO: update your UI with the user information
            } else {
                // Sign in failed, display a message and update the UI
                Log.w(TAG, "signInWithCredential:failure", task.exception)
            }
        }
}*/
