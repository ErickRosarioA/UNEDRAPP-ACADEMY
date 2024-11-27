package com.developer.edra.unedrappacademy.android.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.domain.model.UserLogged
import com.developer.edra.unedrappacademy.android.domain.use_case.GetCurrentUserUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.LogoutUseCase
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
    }

    private val _userLogged = MutableStateFlow(UserLogged())
    val userLogged: StateFlow<UserLogged> get() = _userLogged.asStateFlow()

    private val _refreshEvent = MutableStateFlow(true)
    val refreshEvent: StateFlow<Boolean> = _refreshEvent

    fun triggerRefresh() {
        _refreshEvent.value = true
    }

    fun clearRefresh() {
        _refreshEvent.value = false
    }

    fun getCurrentUser() {
        getCurrentUserUseCase(CallbackHandle(
            onSuccess = {
                _userLogged.value = _userLogged.value.copy(email = it.email)
            },
            onError = {}
        ))
    }


    fun logout(logoutResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            logoutUseCase(CallbackHandle(
                onSuccess = {
                    logoutResult.invoke(it)
                },
                onError = {}
            ))
        }
    }
}
