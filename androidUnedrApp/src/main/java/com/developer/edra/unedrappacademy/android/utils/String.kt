package com.developer.edra.unedrappacademy.android.utils

fun String.capitalizeEachWord(): String {
    return this.split(" ").joinToString(" ") {
        it.lowercase().replaceFirstChar { char -> char.uppercase() }
    }
}