package com.laomuji666.compose.core.logic.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageInfoEntity(
    val account:Long,
    val timestamp: Long = System.currentTimeMillis(),
    val text:String,
    val isSend:Boolean
){
    @PrimaryKey(autoGenerate = true)
    var messageId:Long = 0
}