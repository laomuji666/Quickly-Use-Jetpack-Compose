package com.laomuji666.compose.feature.chat.chat

import com.laomuji666.compose.core.logic.database.entity.MessageInfoEntity

data class ChatScreenUiState(
    val sendAvatar: String = "",
    val receiveAvatar: String = "",
    val nickname:String = "",
    val messageList: List<MessageInfoEntity> = emptyList(),
    val inputText:String = "",
)
