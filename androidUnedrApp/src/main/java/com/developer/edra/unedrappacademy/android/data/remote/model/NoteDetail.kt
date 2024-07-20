package com.developer.edra.unedrappacademy.android.data.remote.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class NoteDetail(
    @get:PropertyName("Id")
    val id: Int = 0,

    @get:PropertyName("AsignaturaId")
    val subjectId: Int = 0,

    @get:PropertyName("P1")
    val p1: Int = 0,

    @get:PropertyName("P2")
    val p2: Int = 0,

    @get:PropertyName("PA")
    val pa: Int = 0,

    @get:PropertyName("AS")
    val ass: Int = 0,

    @get:PropertyName("NotaTotal")
    val totalNote: Int = 0,

    @get:PropertyName("Literal")
    val literal: String = ""
) : Parcelable