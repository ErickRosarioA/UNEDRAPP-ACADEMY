package com.developer.edra.unedrappacademy.android.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class NoteDetail(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,

    @get:PropertyName("AsignaturaId") @set:PropertyName("AsignaturaId")
    var subjectId: Int = 0,

    @get:PropertyName("P1") @set:PropertyName("P1")
    var p1: Int = 0,

    @get:PropertyName("P2") @set:PropertyName("P2")
    var p2: Int = 0,

    @get:PropertyName("PA") @set:PropertyName("PA")
    var pa: Int = 0,

    @get:PropertyName("AS") @set:PropertyName("AS")
    var ass: Int = 0,

    @get:PropertyName("NotaTotal") @set:PropertyName("NotaTotal")
    var totalNote: Int = 0,

    @get:PropertyName("Literal") @set:PropertyName("Literal")
    var literal: String = ""
) : Parcelable