package com.developer.edra.unedrappacademy.android.data.repository

import com.developer.edra.unedrappacademy.android.domain.model.UserLogged
import com.developer.edra.unedrappacademy.android.domain.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthFirebaseImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    override fun getCurrentUser(callback: CallbackHandle<UserLogged>) {
        try {
            val currentUser = auth.currentUser
            val userLogged = UserLogged(
                uid = currentUser!!.uid,
                email = currentUser.email.toString()
            )
            callback.onSuccess.invoke(userLogged)
        } catch (e: Exception) {
            callback.onError.invoke(null)
        }

    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
        callback: CallbackHandle<Boolean>
    ) {
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                callback.onSuccess.invoke(it.isSuccessful)
            }
        } catch (e: Exception) {
            callback.onError.invoke(null)
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        callback: CallbackHandle<Boolean>

    ) {
        try {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                callback.onSuccess.invoke(it.isSuccessful)
            }
        } catch (e: Exception) {
            callback.onError.invoke(null)
        }
    }

    override fun logout(callback: CallbackHandle<Boolean>) {
        try {
            auth.signOut()
            callback.onSuccess(true)
        } catch (e: Exception) {
            callback.onError(null)
        }
    }

}