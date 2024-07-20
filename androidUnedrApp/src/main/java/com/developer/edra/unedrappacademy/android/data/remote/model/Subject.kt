package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    @get:PropertyName("Id")
    val id: Int = 0,

    @get:PropertyName("Código")
    val code: String = "",

    @get:PropertyName("NombreAsignatura")
    val subjectName: String = "",

    @get:PropertyName("Crédito")
    val credit: Int = 0,

    @get:PropertyName("HT")
    val theoryHours: Int = 0,

    @get:PropertyName("HP")
    val practicalHours: Int = 0,

    @get:PropertyName("HI")
    val researchHours: Int = 0,

    @get:PropertyName("TH")
    val totalHours: Int = 0,

    @get:PropertyName("Prerrequisito")
    val prerequisite: Int = 0,

    @get:PropertyName("Cuatrimestre")
    val quarter: Int = 0
) : Parcelable