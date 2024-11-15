package com.laomuji666.compose.core.logic.location

import android.content.Context
import android.location.Location

interface Locator {
    fun isEnableGps(): Boolean
    fun openGpsSetting(activityContext: Context)
    suspend fun getCurrentLocation(): Location?
}