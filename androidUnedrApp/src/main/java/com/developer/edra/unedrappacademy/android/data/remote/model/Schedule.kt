package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Schedule(
    @get:PropertyName("Id")
    val id: Int = 0,

    @get:PropertyName("AsignaturaId")
    val subjectId: Int = 0,

    @get:PropertyName("Dia")
    val day: String = "",

    @get:PropertyName("Aula")
    val classroom: String = "",

    @get:PropertyName("Modalidad")
    val modality: String = "",

    @get:PropertyName("HoraInicio")
    val startTime: String = "",

    @get:PropertyName("HoraFin")
    val endTime: String = "",

    @get:PropertyName("Profesor")
    val teacher: String = ""
) : Parcelable
