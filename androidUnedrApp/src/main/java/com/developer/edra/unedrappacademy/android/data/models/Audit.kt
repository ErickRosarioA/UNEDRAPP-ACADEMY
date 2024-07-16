package com.developer.edra.unedrappacademy.android.data.models

data class Audit (
    val id: Int = 0,
    val studentId: Int = 0,
    val universityAudit: List<AuditDetail> = emptyList()
)