package com.laomuji666.compose.core.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.stateInTimeout(
    scope: CoroutineScope,
    initialValue: T,
    timeoutMillis: Long = 5000
): StateFlow<T> {
    return stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(timeoutMillis),
        initialValue = initialValue
    )
}