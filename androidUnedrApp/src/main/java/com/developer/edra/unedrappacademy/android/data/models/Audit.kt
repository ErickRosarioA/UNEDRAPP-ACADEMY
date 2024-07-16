package com.developer.edra.unedrappacademy.android.data.models

import com.google.gson.annotations.SerializedName


data class Audit(
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("estudianteId") val studentId: Int = 0,
    @SerializedName("AuditoriaUniversitaria") val universityAudit: List<AuditDetail> = listOf()
)