package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Period(
    @get:PropertyName("Id")
    val id: Int = 0,

    @get:PropertyName("Codigo")
    val code: String = "",

    @get:PropertyName("Meses")
    val months: String = "",

    @get:PropertyName("Año")
    val year: Int = 0
) : Parcelable