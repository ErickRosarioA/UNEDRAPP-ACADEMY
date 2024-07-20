package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class NoteActive(
    @get:PropertyName("Id")
    val id: Int = 0,

    @get:PropertyName("EstudianteId")
    val studentId: Int = 0,

    @get:PropertyName("Notas")
    val notes: List<NoteDetail> = emptyList()
) : Parcelable