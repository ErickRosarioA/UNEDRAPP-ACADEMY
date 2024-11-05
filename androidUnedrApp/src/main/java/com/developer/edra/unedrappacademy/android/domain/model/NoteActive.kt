package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class NoteActive(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("EstudianteId") @set:PropertyName("EstudianteId")
    var studentId: Int = 0,

    @get:PropertyName("Notas") @set:PropertyName("Notas")
    var notes: List<NoteDetail> = emptyList()
) : Parcelable