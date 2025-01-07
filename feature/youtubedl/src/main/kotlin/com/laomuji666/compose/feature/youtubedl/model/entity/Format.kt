package com.laomuji666.compose.feature.youtubedl.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Format(
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
) {
    fun isAudioOnly(): Boolean = vcodec == null || vcodec == "none"

    fun isVideoOnly(): Boolean = acodec == null || acodec == "none"

    fun containsVideo(): Boolean = vcodec != null && vcodec != "none"

    fun containsAudio(): Boolean = acodec != null && acodec != "none"
}