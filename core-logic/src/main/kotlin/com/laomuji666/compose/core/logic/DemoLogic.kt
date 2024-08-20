package com.laomuji666.compose.core.logic

import kotlinx.coroutines.delay

class DemoLogic {
    suspend fun requestMainText():String{
        delay(1000)
        return "Compose"
    }
}