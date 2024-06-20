package com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.screens

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.opdroid.whatsapp.firebaseproject.common.CommonDialog
import com.opdroid.whatsapp.firebaseproject.common.OtpView
import com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.ui.AuthViewModel
import com.opdroid.whatsapp.firebaseproject.utils.ResultState
import com.opdroid.whatsapp.firebaseproject.utils.showMsg
import com.opdroid.whatsapp.theme.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PhoneAuthScreen(
    activity: Activity,
    viewModel: AuthViewModel = hiltViewModel(),
    preferencesManager: PreferencesManager,
    saveOwnerDetail:(Long) -> Unit,
    isAuthSuccessful:(Boolean)->Unit
) {
    var mobile by remember { mutableStateOf("")}
    var otp by remember { mutableStateOf("")}
    val scope = rememberCoroutineScope()
    var isDialog by remember{ mutableStateOf(false)}

    if(isDialog)
        CommonDialog()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Enter Mobile Number")
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = mobile, onValueChange = {
                mobile = it
            }, label = {Text(preferencesManager.getOwnerCountryCode())}, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { 
                scope.launch(Dispatchers.Main){
                    viewModel.createUserWithPhone(
                        mobile,
                        activity
                    ).collect{
                        when(it){
                            is ResultState.Success->{
                                isDialog = false
                                activity.showMsg(it.data)
                            }
                            is ResultState.Failure->{
                                isDialog = false
                                activity.showMsg(it.msg.toString())
                            }
                            ResultState.Loading->{
                                isDialog = true
                            }
                        }
                    }
                }
            }) {
                Text(text = "Submit")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Enter Otp")
            Spacer(modifier = Modifier.height(20.dp))
            OtpView(otpText = otp){
                otp = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                scope.launch(Dispatchers.Main){
                    viewModel.signInWithCredential(
                        otp
                    ).collect{
                        when(it){
                            is ResultState.Success->{
                                isDialog = false
                                activity.showMsg(it.data)
                                saveOwnerDetail(mobile.toLong())
                                isAuthSuccessful(true)
                            }
                            is ResultState.Failure->{
                                isDialog = false
                                activity.showMsg(it.msg.toString())
                                isAuthSuccessful(false)
                            }
                            ResultState.Loading->{
                                isDialog = true
                            }
                        }
                    }
                }
            }) {
                Text(text = "Verify")
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }

}