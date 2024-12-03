package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Career(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("Nombre") @set:PropertyName("Nombre")
    var name: String = "",

    @get:PropertyName("Descripción") @set:PropertyName("Descripción")
    var description: String = "",

    @get:PropertyName("TotalPeriodosAcademicosCuatrimestrales") @set:PropertyName("TotalPeriodosAcademicosCuatrimestrales")
    var totalQuarterlyAcademicPeriods: Int = 0,

    @get:PropertyName("TotalAsignaturas") @set:PropertyName("TotalAsignaturas")
    var totalSubjects: Int = 0,

    @get:PropertyName("CreditoTotal") @set:PropertyName("CreditoTotal")
    var totalCredits: Int = 0,

    @get:PropertyName("TotalHorasTeoricas") @set:PropertyName("TotalHorasTeoricas")
    var totalTheoryHours: Int = 0,

    @get:PropertyName("TotalHorasPracticas") @set:PropertyName("TotalHorasPracticas")
    var totalPracticalHours: Int = 0,

    @get:PropertyName("TotalHorasInvestigacion") @set:PropertyName("TotalHorasInvestigacion")
    var totalResearchHours: Int = 0,

    @get:PropertyName("TotalHoras") @set:PropertyName("TotalHoras")
    var totalHours: Int = 0,

    @get:PropertyName("Asignaturas") @set:PropertyName("Asignaturas")
    var subjects: List<Subject> = listOf()
) : Parcelable