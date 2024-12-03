package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    @get:PropertyName("Id")
    @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("Nombres")
    @set:PropertyName("Nombres")
    var firstName: String = "",

    @get:PropertyName("Apellidos")
    @set:PropertyName("Apellidos")
    var lastName: String = "",

    @get:PropertyName("EstatusUniversitario")
    @set:PropertyName("EstatusUniversitario")
    var universityStatus: Boolean = false,

    @get:PropertyName("Recinto")
    @set:PropertyName("Recinto")
    var campus: String = "",

    @get:PropertyName("Matricula")
    @set:PropertyName("Matricula")
    var enrollment: String = "",

    @get:PropertyName("Correo")
    @set:PropertyName("Correo")
    var email: String = "",

    @get:PropertyName("Telefono")
    @set:PropertyName("Telefono")
    var phone: String = "",

    @get:PropertyName("CarreraId")
    @set:PropertyName("CarreraId")
    var careerId: Int = 0,

    @get:PropertyName("AuditoriaId")
    @set:PropertyName("AuditoriaId")
    var auditId: Int = 0,

    @get:PropertyName("NotasActivasId")
    @set:PropertyName("NotasActivasId")
    var activeGradesId: Int = 0
) : Parcelable

