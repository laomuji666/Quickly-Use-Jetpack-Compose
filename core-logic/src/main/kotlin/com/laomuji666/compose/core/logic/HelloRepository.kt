package com.laomuji666.compose.core.logic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class HelloRepository {
    fun requestHelloText() = flow {
        delay(3000)
        emit("Compose")
    }
}