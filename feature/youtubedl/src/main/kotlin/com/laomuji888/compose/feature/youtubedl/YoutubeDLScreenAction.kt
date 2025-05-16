package com.laomuji888.compose.feature.youtubedl

import com.laomuji888.compose.feature.youtubedl.model.DownloadInfo

sealed interface YoutubeDLScreenAction {
    data class SetUrl(val url: String) : YoutubeDLScreenAction
    data object OnDownloadVideoClick : YoutubeDLScreenAction
    data class SwitchDownloadVideo(val downloadInfo: DownloadInfo) : YoutubeDLScreenAction
}