package com.developer.edra.unedrappacademy.android.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.data.remote.model.Resource
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.developer.edra.unedrappacademy.android.data.remote.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.data.remote.repository.DataRepositoryImpl
import com.developer.edra.unedrappacademy.android.ui.schedule.ScheduleViewModel.UIState
import com.developer.edra.unedrappacademy.android.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataRepositoryImpl: DataRepositoryImpl
) :
    ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var registerMessage = MutableStateFlow(false)
        private set

    fun signUp(authResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            authRepository.createUserWithEmailAndPassword(email, password, CallbackHandle(
                onSuccess = { authResult.invoke(it) },
                onError = { }
            ))
        }
    }


    fun postStudent(student: Student) {
        viewModelScope.launch {
            dataRepositoryImpl.postStudent(student).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Manejar estado de carga, por ejemplo, mostrar un indicador de progreso
                    }

                    is Resource.Error -> {
                        registerMessage.value = false
                    }

                    is Resource.Success -> {
                        registerMessage.value = true
                        delay(3000)
                    }
                }
            }
        }
    }
}