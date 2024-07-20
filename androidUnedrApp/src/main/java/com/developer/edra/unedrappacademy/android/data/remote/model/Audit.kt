package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Audit(
    @get:PropertyName("Id")
    val id: Int = 0,

    @get:PropertyName("estudianteId")
    val studentId: Int = 0,

    @get:PropertyName("AuditoriaUniversitaria")
    val universityAudit: List<AuditDetail> = listOf()
) : Parcelable