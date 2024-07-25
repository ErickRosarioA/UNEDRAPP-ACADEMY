package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuditDetail(
    @get:PropertyName("AsignaturaId") @set:PropertyName("AsignaturaId")
    var subjectId: Int = 0,

    @get:PropertyName("Estado") @set:PropertyName("Estado")
    var status: String = "",

    @get:PropertyName("HorarioId") @set:PropertyName("HorarioId")
    var scheduleId: Int = 0,

    @get:PropertyName("IdNota") @set:PropertyName("IdNota")
    var noteId: Int = 0,

    @get:PropertyName("Nota") @set:PropertyName("Nota")
    var note: Int = 0,

    @get:PropertyName("PeriodoId") @set:PropertyName("PeriodoId")
    var periodId: Int = 0
) : Parcelable