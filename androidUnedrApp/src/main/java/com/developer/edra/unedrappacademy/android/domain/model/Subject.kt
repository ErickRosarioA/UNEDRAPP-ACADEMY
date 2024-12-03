package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("Código") @set:PropertyName("Código")
    var code: String = "",

    @get:PropertyName("NombreAsignatura") @set:PropertyName("NombreAsignatura")
    var subjectName: String = "",

    @get:PropertyName("Crédito") @set:PropertyName("Crédito")
    var credit: Int = 0,

    @get:PropertyName("HT") @set:PropertyName("HT")
    var theoryHours: Int = 0,

    @get:PropertyName("HP") @set:PropertyName("HP")
    var practicalHours: Int = 0,

    @get:PropertyName("HI") @set:PropertyName("HI")
    var researchHours: Int = 0,

    @get:PropertyName("TH") @set:PropertyName("TH")
    var totalHours: Int = 0,

    @get:PropertyName("Prerrequisito") @set:PropertyName("Prerrequisito")
    var prerequisite: Int = 0,

    @get:PropertyName("Cuatrimestre") @set:PropertyName("Cuatrimestre")
    var quarter: Int = 0
) : Parcelable