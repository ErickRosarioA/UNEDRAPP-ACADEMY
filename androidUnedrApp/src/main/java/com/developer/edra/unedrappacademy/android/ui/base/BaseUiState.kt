package com.developer.edra.unedrappacademy.android.ui.base

import androidx.compose.runtime.MutableState
import com.developer.edra.unedrappacademy.android.R
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseUiState(
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false),
    var hasError: Pair<Boolean, Int> =  Pair(false, R.string.empty_text)
)