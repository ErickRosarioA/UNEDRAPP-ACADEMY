package com.developer.edra.unedrappacademy.android.di

import com.developer.edra.unedrappacademy.android.data.remote.repository.AuthFirebaseImpl
import com.developer.edra.unedrappacademy.android.data.remote.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.data.remote.repository.DataRepository
import com.developer.edra.unedrappacademy.android.data.remote.repository.DataRepositoryImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository {
        return AuthFirebaseImpl(auth)
    }

    @Singleton
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Singleton
    @Provides
    fun provideDataRepositoryImpl(db: FirebaseDatabase): DataRepository {
        return DataRepositoryImpl(db)
    }
}