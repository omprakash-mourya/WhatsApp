package com.opdroid.whatsapp.firebaseproject.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun providesRealtimeDb():DatabaseReference =
        Firebase.database.reference.child("user")

    @Singleton
    @Provides
    fun providesFirestoreDb():FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun providesFirebaseAuth():FirebaseAuth = Firebase.auth

}

/*
interface FirebaseModule{
    val databaseReference: DatabaseReference
    val firestoreDb: FirebaseFirestore
    val firebaseAuth: FirebaseAuth
}

class FirebaseModuleImpl(
    private val appContext: Context
): FirebaseModule {
    override val databaseReference: DatabaseReference by lazy {
        Firebase.database.reference.child("user")
    }
    override val firestoreDb: FirebaseFirestore by lazy {
        Firebase.firestore
    }
    override val firebaseAuth: FirebaseAuth by lazy {
        Firebase.auth
    }
//        get() = TODO("Not yet implemented")
}*/
