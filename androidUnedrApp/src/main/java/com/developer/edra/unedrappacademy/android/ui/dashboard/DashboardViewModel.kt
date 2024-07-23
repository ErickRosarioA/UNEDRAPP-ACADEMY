package com.developer.edra.unedrappacademy.android.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.data.remote.model.Career
import com.developer.edra.unedrappacademy.android.data.remote.model.Resource
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.developer.edra.unedrappacademy.android.data.remote.repository.DataRepository
import com.developer.edra.unedrappacademy.android.ui.base.BaseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {


    var uiState = MutableStateFlow(UIState())
        private set


    private fun fetchStudentByEmail(email: String) {
        viewModelScope.launch {
            dataRepository.getStudentByEmail(email).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        uiState.value = uiState.value.copy(student = resource.data!!)
                        fetchCareerById(uiState.value.student.careerId)

                    }

                    is Resource.Error -> {
                        // Manejar el error si el estudiante no se encuentra
                    }

                    is Resource.Loading -> {
                        // Manejar el estado de carga si es necesario
                    }
                }
            }
        }

    }


    private fun fetchCareerById(id: Int) {
        viewModelScope.launch {
            dataRepository.getCareerById(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        uiState.value = uiState.value.copy(career = resource.data!!)
                    }

                    is Resource.Error -> {
                        // Manejar el error si la carrera no se encuentra
                    }

                    is Resource.Loading -> {
                        // Manejar el estado de carga si es necesario
                    }
                }
            }
        }
    }


//    private fun observeCareerState() {
//        viewModelScope.launch {
//            _careerState.collect { career ->
//                career?.let {
//                    _uiState.value = _uiState.value.copy(
//                        carrera = it.name
//                    )
//                }
//            }
//        }
//    }


    data class UIState(
        val student: Student = Student(
            id = -1,
            firstName = "",
            lastName = "",
            universityStatus = false,
            campus = "",
            enrollment = "",
            email = "",
            phone = "",
            careerId = 0,
            auditId = 0,
            activeGradesId = 0
        ),

        val career: Career = Career(
            id = -1,
            name = "",
            description = "",
            totalQuarterlyAcademicPeriods = 0,
            totalSubjects = 0,
            totalCredits = 0,
            totalTheoryHours = 0,
            totalPracticalHours = 0,
            totalResearchHours = 0,
            totalHours = 0,
            subjects = listOf()
        )
    ) : BaseUiState()


    fun onUIEvent(event: UIEvent) {
        when (event) {
            is UIEvent.OnGetStudentByEmail -> fetchStudentByEmail(event.email)
        }
    }


    sealed class UIEvent {
        data class OnGetStudentByEmail(val email: String) : UIEvent()
    }

}
