package com.laomuji666.compose.feature.chat.me

sealed interface MeScreenAction {
    data object SwitchEnableNotification : MeScreenAction
}