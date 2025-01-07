package com.laomuji666.compose.feature.youtubedl.model

import android.content.Context
import com.laomuji666.compose.feature.youtubedl.model.entity.VideoInfo
import com.yausername.aria2c.Aria2c
import com.yausername.ffmpeg.FFmpeg
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLException
import com.yausername.youtubedl_android.YoutubeDLRequest
import com.yausername.youtubedl_android.YoutubeDLResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeDLServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : YoutubeDLService {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
        encodeDefaults = true
    }

    init {
        try {
            YoutubeDL.init(context)
            FFmpeg.init(context)
            Aria2c.init(context)
        }catch (e: YoutubeDLException){
            e.printStackTrace()
        }
    }

    private fun getCachePath(): File {
        val downloadPath = File(context.externalCacheDir, "YoutubeDLUtil")
        if (!downloadPath.exists()) {
            downloadPath.mkdirs()
        }
        return downloadPath
    }

    private fun VideoInfo.getFileName(): String{
        return getCachePath().absolutePath + "/" + title + "." + ext
    }

    private fun getVideoInfoFromUrl(
        url: String
    ): Result<VideoInfo> {
        return url.runCatching {
            val request = YoutubeDLRequest(url).apply {
                addOption("-o", "%(title).200B")
                addOption("--dump-single-json")
                addOption("-R", "1")
                addOption("--no-playlist")
                addOption("--socket-timeout", "5")
            }
            val processId = System.currentTimeMillis()
            val response: YoutubeDLResponse = YoutubeDL.getInstance().execute(request, processId.toString(), null)
            json.decodeFromString<VideoInfo>(response.out)
        }
    }

    private fun downloadVideo(
        videoInfo: VideoInfo,
        callback:  ((Float, Long, String) -> Unit)? = null
    ): Boolean {
        videoInfo.runCatching {
            val url = videoInfo.originalUrl ?: videoInfo.webpageUrl ?: return false
            val request = YoutubeDLRequest(url)
            request.apply {
                addOption("--no-mtime")
                addOption("--downloader", "libaria2c.so")
                addOption("-f", "bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best");
                addOption("-o", videoInfo.getFileName())
            }
            YoutubeDL.execute(
                request = request,
                processId = videoInfo.id,
                callback = callback
            )
        }
        return true
    }


    private val downloadInfoList = MutableStateFlow<List<DownloadInfo>>(emptyList())
    private val downloadInfoMap = mutableMapOf<String, DownloadInfo>()
    private fun setDownloadInfo(id: String, downloadInfo: DownloadInfo){
        downloadInfoMap[id] = downloadInfo
        downloadInfoList.value = downloadInfoMap.values.toList()
    }

    override fun downloadVideo(
        url: String,
        onGetInfoCallback:()->Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            var downloadInfo = DownloadInfo(
                id = System.currentTimeMillis().toString(),
                title = url
            )

            getVideoInfoFromUrl(url)
                .onSuccess {
                    downloadInfo = downloadInfo.copy(
                        title = it.title,
                        duration = it.duration?:0.0,
                        fileSize = it.fileSize ?: it.fileSizeApprox ?: .0,
                        filename = it.getFileName()
                    )
                    setDownloadInfo(downloadInfo.id, downloadInfo)

                    onGetInfoCallback()
                    downloadVideo(videoInfo = it){ progress,_,_ ->
                        downloadInfo = downloadInfo.copy(
                            progress = progress
                        )
                        setDownloadInfo(downloadInfo.id, downloadInfo)
                    }

                    downloadInfo = downloadInfo.copy(
                        progress = 100f,
                        isDone = true
                    )
                    setDownloadInfo(downloadInfo.id, downloadInfo)
                }
                .onFailure {
                    downloadInfo = downloadInfo.copy(
                        isError = true
                    )
                    setDownloadInfo(downloadInfo.id, downloadInfo)
                }
        }
    }

    override fun getDownloadInfoList(): MutableStateFlow<List<DownloadInfo>> {
        return downloadInfoList
    }
}