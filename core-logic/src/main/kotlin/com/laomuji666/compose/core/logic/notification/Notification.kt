package com.laomuji666.compose.core.logic.notification

import javax.inject.Singleton

@Singleton
interface Notification {
    fun updatePushToken(callback:(String)->Unit)
}