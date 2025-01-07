package com.laomuji666.compose.feature.youtubedl

sealed interface YoutubeDLScreenAction {
    data class SetUrl(val url: String) : YoutubeDLScreenAction
    data object OnDownloadVideoClick : YoutubeDLScreenAction
}