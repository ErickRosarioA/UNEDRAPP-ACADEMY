package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Career(
    @get:PropertyName("Id")
    val id: Int = 0,

    @get:PropertyName("Nombre")
    val name: String = "",

    @get:PropertyName("Descripción")
    val description: String = "",

    @get:PropertyName("TotalPeriodosAcademicosCuatrimestrales")
    val totalQuarterlyAcademicPeriods: Int = 0,

    @get:PropertyName("TotalAsignaturas")
    val totalSubjects: Int = 0,

    @get:PropertyName("CreditoTotal")
    val totalCredits: Int = 0,

    @get:PropertyName("TotalHorasTeoricas")
    val totalTheoryHours: Int = 0,

    @get:PropertyName("TotalHorasPracticas")
    val totalPracticalHours: Int = 0,

    @get:PropertyName("TotalHorasInvestigacion")
    val totalResearchHours: Int = 0,

    @get:PropertyName("TotalHoras")
    val totalHours: Int = 0,

    @get:PropertyName("Asignaturas")
    val subjects: List<Subject> = listOf()
) : Parcelable