package com.developer.edra.unedrappacademy.android.domain.use_case

import com.developer.edra.unedrappacademy.android.domain.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle

class LogoutUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(callback: CallbackHandle<Boolean>) {
        authRepository.logout(callback)
    }
}