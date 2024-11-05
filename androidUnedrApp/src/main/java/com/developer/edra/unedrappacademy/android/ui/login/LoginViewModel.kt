package com.developer.edra.unedrappacademy.android.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.domain.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    fun login(authResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            authRepository.signInWithEmailAndPassword(email, password, CallbackHandle(
                onSuccess = { authResult.invoke(it) },
                onError = { }
            ))
        }
    }

}