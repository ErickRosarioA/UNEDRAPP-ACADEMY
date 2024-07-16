package com.developer.edra.unedrappacademy.android.data.models

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("Nombres") val firstName: String = "",
    @SerializedName("Apellidos") val lastName: String = "",
    @SerializedName("EstatusUniversitario") val universityStatus: Boolean = false,
    @SerializedName("Recinto") val campus: String = "",
    @SerializedName("Matricula") val enrollment: String = "",
    @SerializedName("Correo") val email: String = "",
    @SerializedName("Telefono") val phone: String = "",
    @SerializedName("CarreraId") val careerId: Int = 0,
    @SerializedName("AuditoriaId") val auditId: Int = 0,
    @SerializedName("NotasActivasId") val activeGradesId: Int = 0
)
