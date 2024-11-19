package com.developer.edra.unedrappacademy.android.domain.use_case

import com.developer.edra.unedrappacademy.android.domain.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle

class SignInWithEmailAndPasswordUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, callback: CallbackHandle<Boolean>) {
        authRepository.signInWithEmailAndPassword(email, password, callback)
    }
}