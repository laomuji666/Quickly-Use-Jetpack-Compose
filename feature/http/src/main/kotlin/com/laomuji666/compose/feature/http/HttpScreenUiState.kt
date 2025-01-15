package com.laomuji666.compose.feature.http

data class HttpScreenUiState(
    val isError:Boolean = false,
    val isLoading:Boolean = false,
    val isConnect:Boolean = false,
    val responseText:String = "",
)