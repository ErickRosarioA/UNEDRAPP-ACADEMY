package com.developer.edra.unedrappacademy.android.data.models

data class Schedule(
    val id: Int = 0,
    val subjectId: Int = 0,
    val day: String = "",
    val room: String = "",
    val modality: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val teacher: String = ""
)