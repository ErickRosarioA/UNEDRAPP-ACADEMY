package com.developer.edra.unedrappacademy.android.ui.schedule

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.domain.model.Career
import com.developer.edra.unedrappacademy.android.domain.model.Resource
import com.developer.edra.unedrappacademy.android.domain.model.ScheduleDetail
import com.developer.edra.unedrappacademy.android.domain.model.ScheduleSubject
import com.developer.edra.unedrappacademy.android.domain.model.Subject
import com.developer.edra.unedrappacademy.android.domain.repository.DataRepository
import com.developer.edra.unedrappacademy.android.base.BaseUiState
import com.developer.edra.unedrappacademy.android.domain.use_case.DataGeneralUseCases
import com.developer.edra.unedrappacademy.android.utils.getMonthRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel @Inject constructor(private val dataGeneralUseCases: DataGeneralUseCases) :
    ViewModel() {

    var uiState = MutableStateFlow(UIState())
        private set


    private fun fetchStudentByEmail(email: String) {
        viewModelScope.launch {
            dataGeneralUseCases.getStudentByEmail(email).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val studentData = resource.data!!
                        fetchPeriods()
                        fetchCareerById(studentData.careerId)
                        fetchScheduleById(studentData.id)

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


    private fun fetchPeriods() {
        viewModelScope.launch {
            dataGeneralUseCases.getAllPeriods().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                        val periodsListData = resource.data!!

                        val filteredPeriod = periodsListData.find { period ->
                            period.year == currentYear && period.months == getMonthRange()
                        }
                        val periodsActive = filteredPeriod?.let {
                            "${it.months} ${it.year}"
                        } ?: "No Disponible"

                        uiState.value =uiState.value.copy(
                            periodsActive =  periodsActive
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


    private fun fetchScheduleById(id: Int) {
        viewModelScope.launch {
            dataGeneralUseCases.getSchedulesByStudentId(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val scheduleData = resource.data!!
                        val combinedList: MutableList<Pair<ScheduleDetail, Subject>> = mutableListOf()

                        if (uiState.value.career.subjects.isNotEmpty()) {
                            scheduleData.scheduleDetail.forEach { scheduleDetailItem ->
                                val subject = uiState.value.career.subjects.find { it.id == scheduleDetailItem.subjectId }
                                if (subject != null) {
                                    combinedList.add(Pair(scheduleDetailItem, subject))
                                }
                            }

                            val groupedByDay = combinedList.groupBy { it.first.day }

                            val daysOfWeekOrder = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado")
                            val groupedByDayList = groupedByDay.toSortedMap(compareBy { daysOfWeekOrder.indexOf(it) })

                            uiState.value = uiState.value.copy(
                                scheduleAndSubject = ScheduleSubject(
                                    scheduleAndSubject = groupedByDayList.flatMap { entry ->
                                        entry.value
                                    }
                                )
                            )
                        }

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
            dataGeneralUseCases.getCareerById(id).collect { resource ->
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
        val scheduleAndSubject: ScheduleSubject = ScheduleSubject(),
        val career: Career = Career(),
        val periodsActive: String = ""
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