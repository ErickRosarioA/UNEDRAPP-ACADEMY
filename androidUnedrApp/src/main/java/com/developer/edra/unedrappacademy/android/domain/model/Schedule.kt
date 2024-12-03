package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Schedule(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("estudianteId") @set:PropertyName("estudianteId")
    var studentId: Int = 0,

    @get:PropertyName("Horario") @set:PropertyName("Horario")
    var scheduleDetail: List<ScheduleDetail> = listOf(),

    ) : Parcelable