package com.developer.edra.unedrappacademy.android.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.data.remote.model.UserLogged
import com.developer.edra.unedrappacademy.android.data.remote.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _userLogged = MutableStateFlow(UserLogged())
    val userLogged: StateFlow<UserLogged> get() = _userLogged.asStateFlow()


    fun getCurrentUser() {
        authRepository.getCurrentUser(CallbackHandle(
            onSuccess = {
                _userLogged.value = _userLogged.value.copy(email = it.email)
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
