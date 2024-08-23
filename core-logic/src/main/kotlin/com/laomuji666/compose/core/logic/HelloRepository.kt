package com.laomuji666.compose.core.logic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class HelloRepository {
    fun requestHelloText() = flow {
        delay(500)
        emit("Compose")
    }
}