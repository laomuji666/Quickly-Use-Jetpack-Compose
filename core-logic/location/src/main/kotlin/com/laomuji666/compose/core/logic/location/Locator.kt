package com.laomuji666.compose.core.logic.location

interface Locator {
    fun hasLocatorProvider(): Boolean
    suspend fun getCurrentLocation(): LocatorResult
}