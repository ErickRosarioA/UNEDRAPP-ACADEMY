package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduleDetail(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("AsignaturaId") @set:PropertyName("AsignaturaId")
    var subjectId: Int = 0,

    @get:PropertyName("Dia") @set:PropertyName("Dia")
    var day: String = "",

    @get:PropertyName("Aula") @set:PropertyName("Aula")
    var classroom: String = "",

    @get:PropertyName("Modalidad") @set:PropertyName("Modalidad")
    var modality: String = "",

    @get:PropertyName("HoraInicio") @set:PropertyName("HoraInicio")
    var startTime: String = "",

    @get:PropertyName("HoraFin") @set:PropertyName("HoraFin")
    var endTime: String = "",

    @get:PropertyName("Profesor") @set:PropertyName("Profesor")
    var teacher: String = ""
) : Parcelable
