package com.laomuji666.compose.feature.chat.chat

sealed interface ChatScreenAction {
    data class SetInputText(val text: String) : ChatScreenAction
    data object SendInputText : ChatScreenAction
    data object DismissNotification : ChatScreenAction
}