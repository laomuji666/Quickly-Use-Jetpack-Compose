package com.laomuji888.compose.feature.chat

sealed interface AiChatScreenAction {
    data class OnClickContact(val account: Long) : AiChatScreenAction
}