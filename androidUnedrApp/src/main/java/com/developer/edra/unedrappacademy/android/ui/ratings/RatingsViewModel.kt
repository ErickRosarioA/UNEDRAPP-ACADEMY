package com.developer.edra.unedrappacademy.android.ui.ratings

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.unedrappacademy.android.domain.model.Career
import com.developer.edra.unedrappacademy.android.domain.model.NoteActive
import com.developer.edra.unedrappacademy.android.domain.model.NoteDetail
import com.developer.edra.unedrappacademy.android.domain.model.Resource
import com.developer.edra.unedrappacademy.android.domain.model.Subject
import com.developer.edra.unedrappacademy.android.domain.repository.DataRepository
import com.developer.edra.unedrappacademy.android.base.BaseUiState
import com.developer.edra.unedrappacademy.android.utils.getMonthRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingsViewModel @Inject constructor(private val dataRepository: DataRepository) :
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
                        fetchNoteActiveId(studentData.id)
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

    private fun fetchPeriods() {
        viewModelScope.launch {
            dataRepository.getPeriods().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                        val periodsListData = resource.data!!

                        val filteredPeriod = periodsListData.find { period ->
                            period.year == currentYear && period.months == getMonthRange()
                        }
                        val periodsActive = filteredPeriod?.code ?: "No Disponible"

                        uiState.value = uiState.value.copy(
                            codePeriod = periodsActive
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


    private fun fetchNoteActiveId(id: Int) {
        viewModelScope.launch {
            dataRepository.getActiveNotesById(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val noteActivesByStudent = resource.data!!

                        val combinedList: MutableList<Pair<NoteDetail, Subject>> = mutableListOf()

                        if (uiState.value.career.subjects.isNotEmpty()) {
                            noteActivesByStudent.notes.forEach { itemNotes ->
                                val subject =
                                    uiState.value.career.subjects.find { it.id == itemNotes.subjectId }
                                if (subject != null) {
                                    combinedList.add(Pair(itemNotes, subject))
                                }
                            }
                        }

                        uiState.value = uiState.value.copy(
                            noteDetailAndSubject = combinedList
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


    data class UIState(
        val noteActive: NoteActive = NoteActive(),
        val noteDetailAndSubject: List<Pair<NoteDetail, Subject>> = listOf(),
        val codePeriod: String = "",
        val career: Career = Career()
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