package com.laomuji888.compose.feature.chat.me

sealed interface MeScreenAction {
    data object SwitchEnableNotification : MeScreenAction
}