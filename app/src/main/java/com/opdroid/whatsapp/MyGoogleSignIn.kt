package com.opdroid.whatsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.opdroid.whatsapp.theme.ThemeManager
import com.opdroid.whatsapp.ui.theme.WhatsAppTheme

/**
 * Demonstrate Firebase Authentication using a Google ID Token.
 */
class GoogleSignInActivity : ComponentActivity() {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient

    var currentUser = mutableStateOf<FirebaseUser?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.initialize(this)
        enableEdgeToEdge()
        setContent {
            WhatsAppTheme {
                Log.d("GoogleSignInActivity", "onCreate called")
                // [START config_signin]
                // Configure Google Sign In
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.web_client_id))
                    .requestEmail()
                    .build()

                googleSignInClient = GoogleSignIn.getClient(this, gso)
                // [END config_signin]

                // [START initialize_auth]
                // Initialize Firebase Auth
                auth = Firebase.auth
                // [END initialize_auth]
                Log.d("GoogleSignInActivity", "auth initialized")

                GoogleSignUI(user = currentUser.value, signIn = { signIn() })
            }
        }
    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        Log.d("GoogleSignInActivity", "onStart called")
        auth = Firebase.auth
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
        Log.d("GoogleSignInActivity", "UI updated")

    }
    // [END on_start_check_user]

    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        auth = Firebase.auth
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        } else{
            Log.d("GoogleSignInActivity", "requestCode is not RC_SIGN_IN")
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        auth = Firebase.auth

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }
    // [END auth_with_google]

    // [START signin]
    private fun signIn() {
        Log.d("GoogleSignInActivity", "signIn called")
        try {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        } catch (e: Exception) {
            Log.e("GoogleSignInActivity", "Error in signIn", e)
        }
    }
    // [END signin]

    private fun updateUI(user: FirebaseUser?) {
        currentUser.value = user
    }


    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}

@Composable
fun GoogleSignUI(
    user: FirebaseUser?,
    signIn: () -> Unit
    ) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        if (user != null){
            Text(text = "Hello, ${user.displayName}")
            Button(onClick = { user.delete() }){
                Text(text = "Sign out")
            }
        } else {
            Text(text = "Please sign in")
            Button(onClick = signIn) {
                Text(text = "Sign In")
            }
        }
    }

}