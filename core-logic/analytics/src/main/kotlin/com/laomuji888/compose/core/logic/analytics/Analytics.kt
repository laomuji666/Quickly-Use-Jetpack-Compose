package com.laomuji888.compose.core.logic.analytics

import android.os.Bundle
import javax.inject.Singleton

@Singleton
interface Analytics{
    fun logEvent(name:String, bundle: Bundle? = null)
}