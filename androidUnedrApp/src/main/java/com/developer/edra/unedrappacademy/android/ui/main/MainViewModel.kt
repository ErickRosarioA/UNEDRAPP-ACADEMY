package com.developer.edra.unedrappacademy.android.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.data.remote.model.UserLogged
import com.developer.edra.unedrappacademy.android.data.remote.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private var userLogged by mutableStateOf(UserLogged())

    fun getCurrentUser() {
        authRepository.getCurrentUser(CallbackHandle(
            onSuccess = {
                userLogged = userLogged.copy(email = it.email)
            },
            onError = {}
        ))
    }

    fun logout(logoutResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            authRepository.logout(CallbackHandle(
                onSuccess = {
                    logoutResult.invoke(it)
                },
                onError = {}
            ))
        }
    }


}