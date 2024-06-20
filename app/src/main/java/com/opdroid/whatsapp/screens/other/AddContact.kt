package com.opdroid.whatsapp.screens.other

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opdroid.whatsapp.R
import com.opdroid.whatsapp.data.MyContact
//import com.opdroid.whatsapp.realm.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SelectContact(/*mainViewModel: MainViewModel,*/
                  onBackClick: () -> Unit = {},
                  onContactClick: (String) -> Unit = {}
                  ){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()  // Add padding for the status bar
            .navigationBarsPadding()  // Add padding for the navigation bar
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonHeader("Select Contact",onBackClick)
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Save Contact", fontSize = 20.sp)
        }
        LazyColumn {

        }
    }
}

@Composable
fun AddContact(/*mainViewModel: MainViewModel,*/
               onBackClick: () -> Unit = {}){
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()  // Add padding for the status bar
            .navigationBarsPadding()  // Add padding for the navigation bar
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CommonHeader("Add Contact",onBackClick)

        TextFields(textValue = firstName, label = "First Name", R.drawable.account_circle, {firstName = it})
        TextFields(textValue = lastName, label = "Last Name", R.drawable.account_circle, {lastName = it})
        TextFields(textValue = phoneNumber, label = "Phone Number", R.drawable.call, {phoneNumber = it}, onlyNumerical = true)

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val contact = MyContact(mySavedName = (firstName+" "+ lastName), phoneNumber= phoneNumber.toLong())
//                mainViewModel.saveContact(contact)
            }
                         },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.primary
            )
            ) {
            Text("Save Contact", fontSize = 20.sp)
        }
    }
}

@Composable
fun TextFields(textValue:String,
               label:String,
               icon:Int = R.drawable.account_circle,
               onValueChange: (String) -> Unit = {},
               onlyNumerical: Boolean = false
               ){
    OutlinedTextField(value = textValue,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        placeholder = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        singleLine = true,
        leadingIcon = {
            Icon(painter = painterResource(id = icon),
                contentDescription = "icon",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.primary)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            disabledTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
            errorContainerColor = MaterialTheme.colorScheme.error,
            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary,
            cursorColor = MaterialTheme.colorScheme.tertiary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary

        ),
        keyboardOptions =  KeyboardOptions(keyboardType = if(onlyNumerical) KeyboardType.Number else KeyboardType.Text)
    )
}

@Composable
fun CommonHeader(headerName: String,
                 onBackClick: () -> Unit = {}
                 ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "back",
            modifier = Modifier
                .size(30.dp)
                .padding(20.dp)
                .clickable { onBackClick() },
            tint = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = headerName, fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary)
    }
}