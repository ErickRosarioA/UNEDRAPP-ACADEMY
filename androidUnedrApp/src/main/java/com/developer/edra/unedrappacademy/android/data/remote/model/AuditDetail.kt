package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuditDetail(
    @get:PropertyName("AsignaturaId")
    val subjectId: Int = 0,

    @get:PropertyName("Estado")
    val status: String = "",

    @get:PropertyName("HorarioId")
    val scheduleId: Int = 0,

    @get:PropertyName("IdNota")
    val noteId: Int = 0,

    @get:PropertyName("PeriodoId")
    val periodId: Int = 0
) : Parcelable