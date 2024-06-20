package com.opdroid.whatsapp.firebaseproject.features.firestoredb.repository

import com.opdroid.whatsapp.firebaseproject.features.firestoredb.FirestoreModelResponse
import com.opdroid.whatsapp.firebaseproject.features.firestoredb.UserResponse
import com.opdroid.whatsapp.firebaseproject.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {

    fun insert(
        item:FirestoreModelResponse.FirestoreItem
    ) : Flow<ResultState<String>>

    fun getItems() : Flow<ResultState<List<FirestoreModelResponse>>>

    fun delete(key:String) : Flow<ResultState<String>>

    fun update(
        item:FirestoreModelResponse
    ) : Flow<ResultState<String>>

    fun insertUser(
        item:UserResponse.User
    ) : Flow<ResultState<String>>

    fun getUser() : Flow<ResultState<List<UserResponse>>>

    fun updateUser(
        item:UserResponse
    ) : Flow<ResultState<String>>

}