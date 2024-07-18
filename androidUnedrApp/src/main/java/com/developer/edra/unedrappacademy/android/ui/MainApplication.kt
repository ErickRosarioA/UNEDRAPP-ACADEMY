package com.developer.edra.unedrappacademy.android.ui

import android.app.Application
import com.developer.edra.unedrappacademy.android.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // TODO()
        }
    }
}