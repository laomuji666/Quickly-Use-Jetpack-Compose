package com.laomuji1999.compose.feature.chat

sealed interface AiChatScreenAction {
    data class OnClickContact(val account: Long) : AiChatScreenAction
}