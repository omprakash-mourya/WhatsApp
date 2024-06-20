package com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.activity

//import com.opdroid.whatsapp.MainActivity
//import com.opdroid.whatsapp.realm.MainViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material.Surface() {
                Scaffold {
                //    AuthScreen()
                    val padding = it
//                    PhoneAuthScreen(activity = this, ){ isAuthSuccess ->
                        /*if (isAuthSuccess) {
                            navController.navigate("home")
                        }*/
//                    }
                }
            }
        }
    }
}
