package com.developer.edra.unedrappacademy.android.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.domain.use_case.CreateUserWithEmailAndPasswordUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.GetCurrentUserUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.LogoutUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.SignInWithEmailAndPasswordUseCase
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    fun login(authResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            signInWithEmailAndPasswordUseCase(email, password, CallbackHandle(
                onSuccess = { authResult.invoke(it) },
                onError = { }
            ))
        }
    }
}