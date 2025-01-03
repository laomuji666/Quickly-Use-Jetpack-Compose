package com.laomuji666.compose.feature.demo.device

data class DeviceDemoScreenUiState(
    val isLoading: Boolean = false,
    val enableSwitchAppLogo: Boolean = false,
    val location: String = "",
)