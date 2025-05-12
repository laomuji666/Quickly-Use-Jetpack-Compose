package com.laomuji888.compose.feature.biometric

import com.laomuji888.compose.core.logic.authenticate.biometric.BiometricAuthenticate

data class BiometricScreenUiState(
    val title: String = "",
    val description: String = "",
    val biometricResult: BiometricAuthenticate.BiometricAuthenticateResult? = null,
)
