package com.developer.edra.unedrappacademy.android.domain.repository

import com.developer.edra.unedrappacademy.android.domain.model.UserLogged
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle

interface AuthRepository {
    fun getCurrentUser(callback : CallbackHandle<UserLogged>)

    suspend fun signInWithEmailAndPassword(email: String, password: String, callback: CallbackHandle<Boolean>)

    suspend fun createUserWithEmailAndPassword(email: String, password: String, callback: CallbackHandle<Boolean>)

    fun logout(callback: CallbackHandle<Boolean>)
}