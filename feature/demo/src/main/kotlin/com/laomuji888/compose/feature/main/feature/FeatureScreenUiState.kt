package com.laomuji888.compose.feature.main.feature

data class FeatureScreenUiState(
    val isLoading: Boolean = false,
    val enableSwitchAppLogo: Boolean = false,
    val location: String = "",
)