package com.developer.edra.unedrappacademy.android.data.remote.model

import com.google.firebase.database.PropertyName

data class ScheduleSubject(
    val scheduleAndSubject: List<Pair<ScheduleDetail,Subject>> = listOf()
)
