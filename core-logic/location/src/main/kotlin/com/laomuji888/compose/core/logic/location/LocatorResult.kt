package com.laomuji888.compose.core.logic.location

import android.location.Location

/**
 * 定位的返回状态
 */
sealed interface LocatorResult {
    data class Success(val location: Location) : LocatorResult
    data class Error(val e: Exception) : LocatorResult
    data object Cancelled : LocatorResult
    data object PermissionDenied : LocatorResult
    data object ProvidersDisabled : LocatorResult
}