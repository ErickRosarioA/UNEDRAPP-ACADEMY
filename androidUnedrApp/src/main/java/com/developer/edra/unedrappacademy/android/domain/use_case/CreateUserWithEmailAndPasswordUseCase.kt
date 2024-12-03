package com.developer.edra.unedrappacademy.android.domain.use_case

import com.developer.edra.unedrappacademy.android.domain.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle

class CreateUserWithEmailAndPasswordUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, callback: CallbackHandle<Boolean>) {
        authRepository.createUserWithEmailAndPassword(email, password, callback)
    }
}