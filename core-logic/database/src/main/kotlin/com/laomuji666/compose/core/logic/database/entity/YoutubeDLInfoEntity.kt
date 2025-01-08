package com.laomuji666.compose.core.logic.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class YoutubeDLInfoEntity(
    val id: Long,
    val title: String,
    val thumbnail: String?,
    val isError: Boolean = false,
    val isDone: Boolean = false,
    val duration: Double = 0.0,
    val fileSize: Double = 0.0,
    val progress: Float = 0.0f,
){
    @PrimaryKey
    var filename:String = ""
}