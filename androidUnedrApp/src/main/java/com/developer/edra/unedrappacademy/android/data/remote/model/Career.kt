package com.developer.edra.unedrappacademy.android.data.remote.model

import com.google.gson.annotations.SerializedName

data class Career (
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("Nombre") val name: String = "",
    @SerializedName("Descripción") val description: String = "",
    @SerializedName("TotalPeriodosAcademicosCuatrimestrales") val totalQuarterlyAcademicPeriods: Int = 0,
    @SerializedName("TotalAsignaturas") val totalSubjects: Int = 0,
    @SerializedName("CreditoTotal") val totalCredits: Int = 0,
    @SerializedName("TotalHorasTeoricas") val totalTheoryHours: Int = 0,
    @SerializedName("TotalHorasPracticas") val totalPracticalHours: Int = 0,
    @SerializedName("TotalHorasInvestigacion") val totalResearchHours: Int = 0,
    @SerializedName("TotalHoras") val totalHours: Int = 0,
    @SerializedName("Asignaturas") val subjects: List<Subject> = listOf()
)