package com.developer.edra.unedrappacademy.android.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.domain.model.UserLogged
import com.developer.edra.unedrappacademy.android.domain.use_case.GetCurrentUserUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.LogoutUseCase
import com.developer.edra.unedrappacademy.android.service.PushNotificationRealTimeService
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Context,
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

    private fun stopBackgroundService() {
        val serviceIntent = Intent(application, PushNotificationRealTimeService::class.java)
        application.stopService(serviceIntent)
    }

    fun logout(logoutResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            stopBackgroundService()
            logoutUseCase(CallbackHandle(
                onSuccess = {
                    logoutResult.invoke(it)
                },
                onError = {}
            ))
        }
    }
}
