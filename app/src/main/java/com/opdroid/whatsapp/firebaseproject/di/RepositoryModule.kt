package com.opdroid.whatsapp.firebaseproject.di

import com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.repository.AuthRepository
import com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.repository.AuthRepositoryImpl
import com.opdroid.whatsapp.firebaseproject.features.firebaseRealtimeDb.repository.RealtimeDbRepository
import com.opdroid.whatsapp.firebaseproject.features.firebaseRealtimeDb.repository.ReatimeRepository
import com.opdroid.whatsapp.firebaseproject.features.firestoredb.repository.FirestoreDbRepositoryImpl
import com.opdroid.whatsapp.firebaseproject.features.firestoredb.repository.FirestoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
   abstract fun providesRealtimeRepository(
        repo: RealtimeDbRepository
    ): ReatimeRepository

   @Binds
   abstract fun providesFirestoreRepository(
       repo: FirestoreDbRepositoryImpl
   ): FirestoreRepository

   @Binds
   abstract fun providesFirebaseAuthRepository(
       repo: AuthRepositoryImpl
   ): AuthRepository

}

