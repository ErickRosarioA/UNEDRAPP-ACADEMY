package com.developer.edra.unedrappacademy.android.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SplashScreenTheme(content: @Composable () -> Unit) {
    val primaryColor = if (isSystemInDarkTheme()) {
        Color(0xFFE45351)
    } else {
        Color(0xFFE45351)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
    ) {
        content()
    }
}
