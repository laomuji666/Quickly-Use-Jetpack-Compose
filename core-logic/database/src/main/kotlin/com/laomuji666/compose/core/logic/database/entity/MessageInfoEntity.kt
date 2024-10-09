package com.laomuji666.compose.core.logic.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class MessageInfoEntity(
    @PrimaryKey val account:Long,
    val timestamp: Long = System.currentTimeMillis(),
    val text:String,
    val isSend:Boolean
)