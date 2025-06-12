package com.laomuji1999.compose.feature.chat.chat

sealed interface ChatScreenAction {
    data object OnClickBack : ChatScreenAction
    data class SetInputText(val text: String) : ChatScreenAction
    data object SendInputText : ChatScreenAction
    data object DismissNotification : ChatScreenAction
}