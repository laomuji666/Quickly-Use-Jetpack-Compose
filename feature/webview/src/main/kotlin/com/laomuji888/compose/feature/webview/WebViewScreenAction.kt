package com.laomuji888.compose.feature.webview

sealed interface WebViewScreenAction {
    data class OnProgressChanged(val progress: Int) : WebViewScreenAction
    data class OnReceivedTitle(val title: String?) : WebViewScreenAction
    data class OnUrlChanged(val url: String) : WebViewScreenAction
}