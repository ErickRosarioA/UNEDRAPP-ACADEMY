package com.developer.edra.unedrappacademy.android.data.remote.model

import com.google.gson.annotations.SerializedName

data class NoteActive(
    @SerializedName("Id")  val id: Int = 0,
    @SerializedName("EstudianteId") val studentId: Int = 0,
    @SerializedName("Notas") val notes: List<NoteDetail> = emptyList()
)