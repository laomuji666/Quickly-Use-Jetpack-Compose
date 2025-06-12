package com.laomuji1999.compose.feature.chat.me

sealed interface MeScreenAction {
    data object SwitchEnableNotification : MeScreenAction
}