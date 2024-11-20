package com.laomuji666.compose.feature.hello

data class HelloUiState(
    val isLoading: Boolean = false,
    val helloText:String = "",
    val enableSwitchAppLogo:Boolean = false,
    val location: String = ""
)
