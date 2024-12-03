package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Period(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("Codigo") @set:PropertyName("Codigo")
    var code: String = "",

    @get:PropertyName("Meses") @set:PropertyName("Meses")
    var months: String = "",

    @get:PropertyName("Año") @set:PropertyName("Año")
    var year: Int = 0
) : Parcelable