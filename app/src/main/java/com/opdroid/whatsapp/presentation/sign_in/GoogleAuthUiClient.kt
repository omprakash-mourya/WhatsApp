package com.opdroid.whatsapp.presentation.sign_in

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.opdroid.whatsapp.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val context: Context
) {
    private val auth = Firebase.auth

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.web_client_id))
        .requestEmail()
        .build()
    private val googleSignInClient = GoogleSignIn.getClient(context, gso)

    suspend fun signIn(): IntentSenderRequest {
        val signInIntent = googleSignInClient.signInIntent
        val pendingIntent = PendingIntent.getActivity(context, 0, signInIntent,
            PendingIntent.FLAG_IMMUTABLE)
        return IntentSenderRequest.Builder(pendingIntent).build()
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        val account = task.getResult(ApiException::class.java)
//        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = account?.idToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch(e: ApiException) {
            Log.e("GoogleAuth", "ApiException in signInWithIntent", e)
            return SignInResult(
                data = null,
                errorMessage = e.message
            )
        }  catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut() {
        try {
            googleSignInClient.signOut().await() //
            auth.signOut()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}