package com.developer.edra.unedrappacademy.android.ui.selection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.developer.edra.unedrappacademy.android.domain.use_case.GetCurrentUserUseCase
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SelectionViewModel @Inject constructor(private val getCurrentUserUseCase: GetCurrentUserUseCase) :
    ViewModel() {
    var isLogged by mutableStateOf(false)

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        getCurrentUserUseCase(CallbackHandle(
            onSuccess = { isLogged = true },
            onError = {}
        ))
    }
}