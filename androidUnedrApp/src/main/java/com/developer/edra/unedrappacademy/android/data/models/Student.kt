package com.developer.edra.unedrappacademy.android.data.models

data class Student(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val universityStatus: Boolean = false,
    val campus: String = "",
    val enrollment: String = "",
    val email: String = "",
    val phone: String = "",
    val careerId: Int = 0,
    val auditId: Int = 0,
    val activeNotesId: Int = 0
)
