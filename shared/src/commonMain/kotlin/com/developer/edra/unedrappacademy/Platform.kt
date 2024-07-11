package com.developer.edra.unedrappacademy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform