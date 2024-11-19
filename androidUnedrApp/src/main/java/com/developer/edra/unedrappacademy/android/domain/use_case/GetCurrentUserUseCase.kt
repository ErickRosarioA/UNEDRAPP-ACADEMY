package com.developer.edra.unedrappacademy.android.domain.use_case

import com.developer.edra.unedrappacademy.android.domain.model.UserLogged
import com.developer.edra.unedrappacademy.android.domain.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle

class GetCurrentUserUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(callback: CallbackHandle<UserLogged>) {
        authRepository.getCurrentUser(callback)
    }
}