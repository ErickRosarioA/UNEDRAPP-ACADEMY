package com.developer.edra.unedrappacademy.android.data.remote.repository

import com.developer.edra.unedrappacademy.android.data.remote.model.UserLogged
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

interface AuthRepository {
    fun getCurrentUser(callback : CallbackHandle<UserLogged>)

    suspend fun signInWithEmailAndPassword(email: String, password: String, callback: CallbackHandle<Boolean>)

    suspend fun createUserWithEmailAndPassword(email: String, password: String, callback: CallbackHandle<Boolean>)

    fun logout(callback: CallbackHandle<Boolean>)
}