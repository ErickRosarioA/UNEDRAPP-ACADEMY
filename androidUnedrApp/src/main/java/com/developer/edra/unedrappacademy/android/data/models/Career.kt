package com.developer.edra.unedrappacademy.android.data.models

data class Career (
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val totalAcademicPeriods: Int = 0,
    val totalSubjects: Int = 0,
    val totalCredits: Int = 0,
    val totalTheoreticalHours: Int = 0,
    val totalPracticalHours: Int = 0,
    val totalResearchHours: Int = 0,
    val totalHours: Int = 0,
    val subjects: List<Subject> = emptyList()
)