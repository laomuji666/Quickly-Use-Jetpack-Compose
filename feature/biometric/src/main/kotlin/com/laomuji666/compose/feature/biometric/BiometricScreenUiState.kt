package com.laomuji666.compose.feature.biometric

import com.laomuji666.compose.core.logic.authenticate.biometric.BiometricAuthenticate

data class BiometricScreenUiState(
    val title: String = "",
    val description: String = "",
    val biometricResult: BiometricAuthenticate.BiometricAuthenticateResult? = null,
)
