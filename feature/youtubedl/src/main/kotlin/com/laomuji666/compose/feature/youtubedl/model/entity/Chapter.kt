package com.laomuji666.compose.feature.youtubedl.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val title: String? = null,
    @SerialName("start_time") val startTime: Double? = null,
    @SerialName("end_time") val endTime: Double? = null,
)