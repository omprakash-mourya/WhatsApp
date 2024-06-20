package com.opdroid.whatsapp.firebaseproject.features.firestoredb

import android.graphics.Bitmap
import android.provider.ContactsContract

data class FirestoreModelResponse(
    val item:FirestoreItem?,
    val key:String? = ""
){

    data class FirestoreItem(
        val title:String? = "",
        val description:String? = ""
    )

}

data class UserResponse(
    val user:User,
    val key:String? = ""
){
    data class User(
        val uid: String,
        var username: String,
        var email: ContactsContract.CommonDataKinds.Email? = null,
        val phoneNumber: String,
        var profilePicture: Bitmap? = null,
        var profilePictureUrl: String,
        var bio: String = "Hey there! I am using another WhatsApp made by Omprakash.",
        var status: String? = null
    )
}

