package com.laomuji888.compose.feature.youtubedl

import com.laomuji888.compose.feature.youtubedl.model.DownloadInfo

data class YoutubeDLScreenUiState(
    val isLoading: Boolean = false,
    val url: String = "",
    val downloadInfo: List<DownloadInfo> = emptyList()
)
