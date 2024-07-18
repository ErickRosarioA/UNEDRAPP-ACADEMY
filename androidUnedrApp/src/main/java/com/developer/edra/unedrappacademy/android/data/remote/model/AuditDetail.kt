package com.developer.edra.unedrappacademy.android.data.remote.model

import com.google.gson.annotations.SerializedName

data class AuditDetail(
    @SerializedName("AsignaturaId") val subjectId: Int = 0,
    @SerializedName("Estado") val status: String = "",
    @SerializedName("HorarioId") val scheduleId: Int = 0,
    @SerializedName("IdNota")val noteId: Int = 0,
    @SerializedName("PeriodoId") val periodId: Int = 0
)