package com.developer.edra.unedrappacademy.android.ui.dashboard

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.data.remote.model.Audit
import com.developer.edra.unedrappacademy.android.data.remote.model.Career
import com.developer.edra.unedrappacademy.android.data.remote.model.Resource
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.developer.edra.unedrappacademy.android.data.remote.model.Subject
import com.developer.edra.unedrappacademy.android.data.remote.model.SubjectAverage
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
                        fetchAuditById(uiState.value.student.auditId)

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

    private fun fetchAuditById(id: Int) {
        viewModelScope.launch {
            dataRepository.getAuditsById(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val dataAudit = resource.data!!

                        //
                        val completedOrValidatedSubjects =
                            dataAudit.universityAudit.filter { auditDetail ->
                                auditDetail.status == "Completada" || auditDetail.status == "Convalidada"
                            }
                        val approvedCount = completedOrValidatedSubjects.size
                        val totalCount = dataAudit.universityAudit.size
                        val summaryString = "$approvedCount / $totalCount"

                        //
                        val subjectAverageList: MutableList<SubjectAverage> = mutableListOf()
                        val completedOSubjects = dataAudit.universityAudit.filter { auditDetail ->
                            auditDetail.status == "Completada"
                        }
                        completedOSubjects.map { auditSubject ->
                            uiState.value.career.subjects.map {
                                if (auditSubject.subjectId == it.id) {
                                    subjectAverageList.add(
                                        SubjectAverage(
                                            auditSubject.note.toDouble(),
                                            it.credit
                                        )
                                    )
                                }
                            }
                        }

                        val averageString = "${calculateWeightedGPA(subjectAverageList)}"
                        //
                        val selectedSubjects = dataAudit.universityAudit.filter { auditDetail ->
                            auditDetail.status == "Seleccionada"
                        }

                        val subjectListSelected: MutableList<Subject> = mutableListOf()

                        selectedSubjects.map { selectedSubject ->
                            uiState.value.career.subjects.map { subject ->
                                if (selectedSubject.subjectId == subject.id) {
                                    subjectListSelected.add(
                                        Subject(
                                            subjectName = subject.subjectName,
                                            code = subject.code
                                        )
                                    )
                                }
                            }
                        }

                        uiState.value =
                            uiState.value.copy(
                                audit = dataAudit,
                                subjectCount = summaryString,
                                averageString = averageString,
                                subjectSelectedList = subjectListSelected
                            )
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


    private fun convertFinalGrade(grade: Double): Double {
        return (grade / 100) * 4.0
    }

    @SuppressLint("DefaultLocale")
    private fun calculateWeightedGPA(subjects: List<SubjectAverage>): Double {
        val weightedSum = subjects.sumOf { convertFinalGrade(it.noteFinal) * it.credits }
        val totalCredits = subjects.sumOf { it.credits }
        val gpa = if (totalCredits > 0) weightedSum / totalCredits else 0.0
        return String.format("%.1f", gpa).toDouble()
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
        val student: Student = Student(),

        val career: Career = Career(),

        val audit: Audit = Audit(),

        val subjectCount: String = "",

        val averageString: String = "",

        val subjectSelectedList: List<Subject> = listOf()
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
