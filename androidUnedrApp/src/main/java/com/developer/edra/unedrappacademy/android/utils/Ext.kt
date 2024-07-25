package com.developer.edra.unedrappacademy.android.utils

import android.icu.util.Calendar

fun String.capitalizeEachWord(): String {
    return this.split(" ").joinToString(" ") {
        it.lowercase().replaceFirstChar { char -> char.uppercase() }
    }
}


fun getMonthRange(): String {
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
    return when (currentMonth) {
        in 1..4 -> "Enero-Abril"
        in 5..8 -> "Mayo-Agosto"
        in 9..12 -> "Septiembre-Diciembre"
        else -> ""
    }
}