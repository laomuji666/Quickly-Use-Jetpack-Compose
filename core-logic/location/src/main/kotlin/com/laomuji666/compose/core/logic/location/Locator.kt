package com.laomuji666.compose.core.logic.location

import android.location.Location

interface Locator {
    fun isEnableGps(): Boolean
    suspend fun getCurrentLocation(): Location?
}