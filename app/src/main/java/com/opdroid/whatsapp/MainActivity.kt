package com.opdroid.whatsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.opdroid.whatsapp.data.getPhoneNumberCode
import com.opdroid.whatsapp.dummydata.dummyContactItems
import com.opdroid.whatsapp.dummydata.dummyMessage
import com.opdroid.whatsapp.extra.ProgressDialog
import com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.screens.BioDetailScreen
import com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.screens.PhoneAuthScreen
import com.opdroid.whatsapp.presentation.profile.ProfileScreen
import com.opdroid.whatsapp.presentation.sign_in.GoogleAuthUiClient
import com.opdroid.whatsapp.presentation.sign_in.SignInScreen
import com.opdroid.whatsapp.presentation.sign_in.SignInViewModel
import com.opdroid.whatsapp.screens.HomeScreen
import com.opdroid.whatsapp.screens.other.ChattingScreen
import com.opdroid.whatsapp.theme.PreferencesManager
import com.opdroid.whatsapp.theme.ThemeManager
import com.opdroid.whatsapp.ui.theme.WhatsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var preferencesManager: PreferencesManager

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext
//            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        preferencesManager = PreferencesManager(this)

        lifecycleScope.launch {
            val countryCode = getCountryCode()
            preferencesManager.setOwnerCountryCode(countryCode)
        }
        super.onCreate(savedInstanceState)
        ThemeManager.initialize(this)
        enableEdgeToEdge()
        setContent {

            WhatsAppTheme{
//                Login()

                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = if(preferencesManager.isLoggedIn()) "auth" else "auth") {
                    navigation(startDestination = "bio-setting-screen", route = "auth"){
                        composable("login-screen"){
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsState() ///******

                            LaunchedEffect(key1 = Unit) {
                                if(googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("profile")
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if(result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if(state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("profile")
                                    viewModel.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
//                                            IntentSenderRequest.Builder(
//                                                signInIntentSender ?: return@launch
//                                            ).build()
                                            signInIntentSender
                                        )
                                    }
                                }
                            )
                        }
                        composable("profile") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                },
                                onNext = {
                                    navController.navigate("home")
                                }
                            )
                        }
                        composable("registration-screen"){
                            PhoneAuthScreen(activity = this@MainActivity,
                                preferencesManager = preferencesManager,
                                saveOwnerDetail= {phone->
                                    preferencesManager.setOwnerContactNumber(phone = phone)
                                }
                                ){ isAuthSuccess ->
                                if (isAuthSuccess) {
                                    preferencesManager.setLoggedIn(true)
                                    navController.navigate("bio-setting-screen")
                                }
                            }
                        }
                        composable("forgot-password-screen"){
                        }
                        composable("sample-screen"){
                            sample(preferencesManager.getOwnerCountryCode())
                        }
                        composable("bio-setting-screen"){
                            BioDetailScreen(preferencesManager = preferencesManager, onNavigateNext = {
                                navController.navigate("home")
                            })
                        }
                    }
                    navigation(startDestination = "home-screen", route = "home"){
                        composable("home-screen"){
                            HomeScreen(true
                            ) { int ->
                                navController.navigate("chatting-screen")
//                                navController.navigate("auth/login-screen")
                            }
                        }
                        composable("chatting-screen"){
                            ChattingScreen(myContact = dummyContactItems[0], messages = dummyMessage)
                        }
                    }
                    navigation(startDestination = "setting-screen", route = "settings") {
                        composable("setting-screen") {}
                        navigation(startDestination = "account-main-screen", route = "account-settings") {
                        composable("account-main-screen") {}
                        composable("security-notifications") {}
                        composable("email-address") {}
                        composable("two-step-verification") {}
                        composable("change-number") {}
                        composable("request-account-info") {}
                        composable("add-account") {}
                        composable("delete-account") {}
                        }
                        navigation(startDestination = "privacy", route = "privacy-settings") {
                            composable("privacy") {}
                            composable("last-seen-and-online") {}
                            composable("profile-photo") {}
                            composable("about") {}
                            composable("status") {}
                            composable("read-receipts") {}
                            composable("default-message-timer") {}
                            composable("groups") {}
                            composable("live-location") {}
                            composable("calls") {}
                            composable("blocked-contacts") {}
                            composable("app-lock") {}
                            composable("chat-lock") {}
                            composable("advanced") {}
                        }
                        navigation(startDestination = "avatar", route = "avatar-settings") {
                            composable("avatar") {}
                        }
                        navigation(startDestination = "chats-setting", route = "chats-settings") {
                            composable("chats-setting") {}
                            composable("chats-backup") {}
                            composable("transfer-chats") {}
                            composable("chat-history") {}
                        }
                        composable("notifications-settings"){}
                        composable("storage-and-data-settings"){}
                        composable("help"){}
                        composable("invite-a-friend"){}
                        composable("app-update"){}
                    }
                }

                ProgressDialog()
            }
        }
    }

}

suspend fun getCountryCode(): String? = withContext(Dispatchers.IO) {
    val response = URL("http://ip-api.com/json").readText()
    val jsonObj = JSONObject(response)
    val countryCode = jsonObj.getString("countryCode")
//    countryCode
    getPhoneNumberCode(countryCode)
}

@Composable
fun sample(code: String){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = code)
    }
}