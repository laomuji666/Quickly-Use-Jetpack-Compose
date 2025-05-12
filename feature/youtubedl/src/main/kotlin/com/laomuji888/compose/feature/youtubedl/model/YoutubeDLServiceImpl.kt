package com.laomuji888.compose.feature.youtubedl.model

import android.content.Context
import com.laomuji888.compose.core.logic.database.dao.YoutubeDLDao
import com.laomuji888.compose.feature.youtubedl.model.DownloadInfo.Companion.toDownloadInfoList
import com.laomuji888.compose.feature.youtubedl.model.DownloadInfo.Companion.toYoutubeDLInfoEntity
import com.yausername.aria2c.Aria2c
import com.yausername.ffmpeg.FFmpeg
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLException
import com.yausername.youtubedl_android.YoutubeDLRequest
import com.yausername.youtubedl_android.YoutubeDLResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeDLServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: YoutubeDLDao
) : YoutubeDLService {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
        encodeDefaults = true
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                YoutubeDL.init(context)
                FFmpeg.init(context)
                Aria2c.init(context)
            }catch (e: YoutubeDLException){
                e.printStackTrace()
            }
            dao.getYoutubeDLInfoListOnce().toDownloadInfoList().forEach { downloadInfo->
                if(!downloadInfo.isDone && downloadInfo.isDownloading){
                    setDownloadInfo(downloadInfo.copy(isDownloading = false))
                }
            }
        }
    }

    private fun getCachePath(): File {
        val downloadPath = File(context.externalCacheDir, "YoutubeDLUtil")
        if (!downloadPath.exists()) {
            downloadPath.mkdirs()
        }
        return downloadPath
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
                addOption("--downloader", "libaria2c.so")
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
                addOption("-f", "bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best");
                addOption("-o", videoInfo.getFilename(getCachePath()))
                addOption("--downloader", "libaria2c.so")
            }
            YoutubeDL.execute(
                request = request,
                processId = videoInfo.getFilename(getCachePath()),
                callback = callback
            )
        }
        return true
    }


    private val downloadInfoList = dao.getYoutubeDLInfoList()
    private fun setDownloadInfo(downloadInfo: DownloadInfo){
        dao.insert(downloadInfo.toYoutubeDLInfoEntity())
    }

    override fun downloadVideo(
        url: String,
        onGetInfoCallback:()->Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            var downloadInfo = DownloadInfo(
                url = url,
                title = url
            )

            getVideoInfoFromUrl(url)
                .onSuccess {
                    onGetInfoCallback()
                    downloadInfo = downloadInfo.copy(
                        title = it.title,
                        thumbnail = it.thumbnail,
                        duration = it.getDuration(),
                        fileSize = it.getFileSize(),
                        filename = it.getFilename(getCachePath()),
                        isDownloading = true
                    )
                    setDownloadInfo(downloadInfo)

                    downloadVideo(videoInfo = it){ progress,_,_ ->
                        downloadInfo = downloadInfo.copy(
                            progress = progress
                        )
                        setDownloadInfo(downloadInfo)
                    }

                    if(File(downloadInfo.filename).exists()){
                        downloadInfo = downloadInfo.copy(
                            progress = 100f,
                            isDone = true
                        )
                        setDownloadInfo(downloadInfo)
                    }else{
                        downloadInfo = downloadInfo.copy(
                            isDownloading = false
                        )
                        setDownloadInfo(downloadInfo)
                    }
                }
                .onFailure {
                    onGetInfoCallback()
                    downloadInfo = downloadInfo.copy(
                        isError = true,
                        isDownloading = false
                    )
                    setDownloadInfo(downloadInfo)
                }
        }
    }

    private fun stopDownload(filename: String, callback: () -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            YoutubeDL.destroyProcessById(filename)
            callback()
        }
    }

    override fun switchDownloadVideo(downloadInfo: DownloadInfo, callback: () -> Unit) {
        if(downloadInfo.isDone){
            return
        }
        if(downloadInfo.isDownloading){
            stopDownload(downloadInfo.filename, callback)
            setDownloadInfo(downloadInfo.copy(isDownloading = false))
        }else{
            downloadVideo(url = downloadInfo.url, onGetInfoCallback = callback)
        }
    }

    override fun getDownloadInfoList(): Flow<List<DownloadInfo>> {
        return downloadInfoList.map { it.toDownloadInfoList() }
    }
}