package com.developer.edra.unedrappacademy.android.data.remote.model

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("AsignaturaId") val subjectId: Int = 0,
    @SerializedName("Dia") val day: String = "",
    @SerializedName("Aula") val classroom: String = "",
    @SerializedName("Modalidad") val modality: String = "",
    @SerializedName("HoraInicio") val startTime: String = "",
    @SerializedName("HoraFin") val endTime: String = "",
    @SerializedName("Profesor") val teacher: String = ""
)