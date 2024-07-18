package com.developer.edra.unedrappacademy.android.data.remote.model

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("Código") val code: String = "",
    @SerializedName("NombreAsignatura") val subjectName: String = "",
    @SerializedName("Crédito") val credit: Int = 0,
    @SerializedName("HT") val theoryHours: Int = 0,
    @SerializedName("HP") val practicalHours: Int = 0,
    @SerializedName("HI") val researchHours: Int = 0,
    @SerializedName("TH") val totalHours: Int = 0,
    @SerializedName("Prerrequisito") val prerequisite: Int = 0,
    @SerializedName("Cuatrimestre") val quarter: Int = 0
)