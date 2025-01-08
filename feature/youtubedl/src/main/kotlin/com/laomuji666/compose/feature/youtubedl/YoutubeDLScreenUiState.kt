package com.laomuji666.compose.feature.youtubedl

import com.laomuji666.compose.feature.youtubedl.model.DownloadInfo

data class YoutubeDLScreenUiState(
    val isLoading: Boolean = false,
    val url: String = "",
    val downloadInfo: List<DownloadInfo> = emptyList()
)
