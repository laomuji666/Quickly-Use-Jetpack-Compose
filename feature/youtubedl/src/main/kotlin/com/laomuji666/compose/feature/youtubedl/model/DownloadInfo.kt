package com.laomuji666.compose.feature.youtubedl.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import java.io.File

/**
 * @param id 下载的唯一标识
 * @param title 下载的标题
 * @param isError 是否有错误
 * @param isDone 是否已经完成
 * @param duration 时长,秒
 * @param fileSize 文件大小, 使用[getFileSizeText]获取
 * @param progress 进度, 百分比
 * @param filename 文件名
 */
data class DownloadInfo(
    val id: String,
    val title: String,
    val isError: Boolean = false,
    val isDone: Boolean = false,
    val duration: Double = 0.0,
    val fileSize: Double = 0.0,
    val progress: Float = 0.0f,
    val filename: String = ""
){
    companion object{
        private const val GB = 1024 * 1024 * 1024
        private const val MB = 1024 * 1024
    }
    @Composable
    fun getDurationText(): String{
        val minute = duration / 60
        val second = duration % 60
        return "${minute.toInt()} ${stringResource(com.laomuji666.compose.res.R.string.string_youtubedl_screen_minute)} ${second.toInt()} ${stringResource(com.laomuji666.compose.res.R.string.string_youtubedl_screen_second)}"
    }
    fun getFileSizeText(): String{
        return if (fileSize > GB) "%.2f GB".format(fileSize / GB)
        else "%.2f MB".format(fileSize / MB)
    }
    fun getProgressText(): String{
        if(progress < 0){
            return "0%"
        }
        return "${progress.toInt()}%"
    }

    fun openVideo(context: Context){
        val uri = filename.runCatching {
                    DocumentFile.fromSingleUri(context, Uri.parse(filename)).run {
                        if (this?.exists() == true) {
                            this.uri
                        } else if (File(this@runCatching).exists()) {
                            FileProvider.getUriForFile(
                                context,
                                "${context.packageName}.provider",
                                File(this@runCatching),
                            )
                        } else null
                    }
                }
                .getOrNull() ?: return

        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            data = uri
            setDataAndType(this.data, data?.let { context.contentResolver.getType(it) } ?: "media/*")

            putExtra(Intent.EXTRA_STREAM, data)
        }
        context.startActivity(intent)
    }

}

