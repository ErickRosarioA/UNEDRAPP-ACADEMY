package com.developer.edra.unedrappacademy.android.data.remote.model

import com.google.gson.annotations.SerializedName

data class Period(
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("Codigo") val code: String = "",
    @SerializedName("Meses") val months: String = "",
    @SerializedName("Año") val year: Int = 0
)
