package com.developer.edra.unedrappacademy.android.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.data.remote.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun signUp(authResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            authRepository.createUserWithEmailAndPassword(email, password, CallbackHandle(
                onSuccess = { authResult.invoke(it) },
                onError = { }
            ))
        }
    }
}