package com.laomuji666.compose.feature.youtubedl.model

import kotlinx.coroutines.flow.Flow

/**
 * 使用 YoutubeDL 进行视频下载
 */
interface YoutubeDLService {
    /**
     * 下载视频
     */
    fun downloadVideo(url: String, onGetInfoCallback: () -> Unit)

    /**
     * 获取下载信息列表
     */
    fun getDownloadInfoList(): Flow<List<DownloadInfo>>
}