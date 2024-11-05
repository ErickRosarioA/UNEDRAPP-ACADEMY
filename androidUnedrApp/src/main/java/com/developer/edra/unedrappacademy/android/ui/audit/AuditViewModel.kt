package com.developer.edra.unedrappacademy.android.ui.audit

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.domain.model.Audit
import com.developer.edra.unedrappacademy.android.domain.model.AuditDetail
import com.developer.edra.unedrappacademy.android.domain.model.Career
import com.developer.edra.unedrappacademy.android.domain.model.Period
import com.developer.edra.unedrappacademy.android.domain.model.Resource
import com.developer.edra.unedrappacademy.android.domain.model.Subject
import com.developer.edra.unedrappacademy.android.domain.repository.DataRepository
import com.developer.edra.unedrappacademy.android.base.BaseUiState
import com.developer.edra.unedrappacademy.android.utils.Constans
import com.developer.edra.unedrappacademy.android.utils.getMonthRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuditViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    var uiState = MutableStateFlow(UIState())
        private set

    private fun fetchStudentByEmail(email: String) {
        viewModelScope.launch {
            dataRepository.getStudentByEmail(email).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val studentData = resource.data!!
                        fetchPeriods()
                        fetchCareerById(studentData.careerId)
                        fetchAuditById(studentData.auditId)

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


    private fun fetchAuditById(id: Int) {
        viewModelScope.launch {
            dataRepository.getAuditsById(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val auditByStudent = resource.data!!
                        val listAuditDetail = auditByStudent.universityAudit

                        val combinedList: MutableList<Triple<AuditDetail, Subject, Period>> =
                            mutableListOf()

                        if (uiState.value.career.subjects.isNotEmpty()) {
                            listAuditDetail.forEach { itemAuditDetail ->
                                val subject =
                                    uiState.value.career.subjects.find { it.id == itemAuditDetail.subjectId }

                                val period = when {
                                    itemAuditDetail.periodId == 0 && itemAuditDetail.status == Constans.STATUS_SEL -> {
                                        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                                        uiState.value.listPeriods.find { period ->
                                            period.year == currentYear && period.months == getMonthRange()
                                        }
                                    }

                                    itemAuditDetail.periodId == 0 && itemAuditDetail.status == Constans.STATUS_PEN -> {
                                        Period(id = 0, code = "0", months = "0", year = 0)
                                    }

                                    else -> {
                                        uiState.value.listPeriods.find { it.id == itemAuditDetail.periodId }
                                    }

                                }

                                itemAuditDetail.literalUse = changesLiteralWord(itemAuditDetail)

                                if (subject != null && period != null) {
                                    combinedList.add(Triple(itemAuditDetail, subject, period))
                                }
                            }
                        }

                        uiState.value = uiState.value.copy(
                            listAuditAndSubject = combinedList
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

   private fun changesLiteralWord(itemAuditDetail: AuditDetail): String {
        return when (itemAuditDetail.status) {
            Constans.STATUS_PEN -> "PE"
            Constans.STATUS_CON -> "CO"
            Constans.STATUS_SEL -> "SEL"
            Constans.STATUS_COM -> {
                when (itemAuditDetail.note) {
                    in 1..69 -> "D"
                    in 70..79 -> "C"
                    in 80..89 -> "B"
                    in 90..100 -> "A"
                    else -> "-"
                }
            }

            else -> itemAuditDetail.status
        }
    }

    private fun fetchPeriods() {
        viewModelScope.launch {
            dataRepository.getPeriods().collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        val periodsListData = resource.data!!

                        uiState.value = uiState.value.copy(
                            listPeriods = periodsListData
                        )
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
                        val careerByStudent = resource.data!!

                        uiState.value = uiState.value.copy(career = careerByStudent)
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

    data class UIState(
        val career: Career = Career(),
        val audit: Audit = Audit(),
        val listAuditAndSubject: List<Triple<AuditDetail, Subject, Period>> = listOf(),
        val listPeriods: List<Period> = listOf()
    ) : BaseUiState()

    fun onUIEvent(event: UIEvent) {
        when (event) {
            is UIEvent.OnGetScheduleByEmail -> fetchStudentByEmail(event.email)
        }
    }


    sealed class UIEvent {
        data class OnGetScheduleByEmail(val email: String) : UIEvent()
    }

}