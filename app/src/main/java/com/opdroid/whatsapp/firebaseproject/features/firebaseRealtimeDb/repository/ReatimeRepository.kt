package com.opdroid.whatsapp.firebaseproject.features.firebaseRealtimeDb.repository

import com.opdroid.whatsapp.firebaseproject.features.firebaseRealtimeDb.RealtimeModelResponse
import com.opdroid.whatsapp.firebaseproject.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface ReatimeRepository {

    fun insert(
        item:RealtimeModelResponse.RealtimeItems
    ) : Flow<ResultState<String>>

    fun getItems() : Flow<ResultState<List<RealtimeModelResponse>>>

    fun delete(
        key:String
    ) : Flow<ResultState<String>>

    fun update(
        res:RealtimeModelResponse
    ) : Flow<ResultState<String>>

}