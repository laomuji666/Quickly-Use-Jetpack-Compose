package com.laomuji888.compose.feature.youtubedl.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class VideoInfo(
    val title: String = "",
    val thumbnail: String? = null,
    val duration: Double? = null,
    @SerialName("webpage_url") val webpageUrl: String? = null,
    @SerialName("original_url") val originalUrl: String? = null,
    val ext: String = "",
    @SerialName("filesize_approx") val fileSizeApprox: Double? = null,
    @SerialName("filesize") val fileSize: Double? = null,
){
    fun getDuration(): Double{
        return duration ?: .0
    }
    fun getFileSize():Double{
        return fileSize ?: fileSizeApprox ?: .0
    }
    fun getFilename(rootPath:File):String{
        return "${rootPath.absolutePath}/${title}_${getFileSize()}.$ext"
    }
}
