package com.developer.edra.unedrappacademy.android.data.models

data class NoteActive(
    val id: Int = 0,
    val studentId: Int = 0,
    val notes: List<NoteDetail> = emptyList()
)