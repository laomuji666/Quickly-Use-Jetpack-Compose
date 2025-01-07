package com.laomuji666.compose.feature.youtubedl.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedDownload(
    @SerialName("requested_formats") val requestedFormats: List<Format>? = emptyList(),
    @SerialName("format_id") val formatId: String? = null,
    @SerialName("format_note") val formatNote: String? = null,
    val ext: String? = null,
    @SerialName("acodec") val acodec: String? = null,
    @SerialName("vcodec") val vcodec: String? = null,
    val url: String? = null,
    val width: Double? = null,
    val height: Double? = null,
    val fps: Double? = null,
    @SerialName("audio_ext") val audioExt: String? = null,
    @SerialName("video_ext") val videoExt: String? = null,
    val format: String? = null,
    val resolution: String? = null,
    val vbr: Double? = null,
    val abr: Double? = null,
    val tbr: Double? = null,
    @SerialName("filesize") val fileSize: Double? = null,
    @SerialName("filesize_approx") val fileSizeApprox: Double? = null,
    val filename: String? = null,
) {
    fun toFormat(): Format =
        Format(
            formatId = formatId,
            formatNote = formatNote,
            ext = ext,
            acodec = acodec,
            vcodec = vcodec,
            url = url,
            width = width,
            height = height,
            fps = fps,
            audioExt = audioExt,
            videoExt = videoExt,
            format = format,
            resolution = resolution,
            vbr = vbr,
            abr = abr,
            tbr = tbr,
            fileSize = fileSize,
            fileSizeApprox = fileSizeApprox,
        )
}
