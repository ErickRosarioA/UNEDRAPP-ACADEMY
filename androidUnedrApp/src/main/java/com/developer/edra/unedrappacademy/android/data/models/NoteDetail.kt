package com.developer.edra.unedrappacademy.android.data.models

import com.google.gson.annotations.SerializedName

data class NoteDetail(
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("AsignaturaId") val subjectId: Int = 0,
    @SerializedName("P1") val p1: Int = 0,
    @SerializedName("P2") val p2: Int = 0,
    @SerializedName("PA") val pa: Int = 0,
    @SerializedName("AS") val ass: Int = 0,
    @SerializedName("NotaTotal") val totalNote: Int = 0,
    @SerializedName("Literal") val literal: String = ""
)