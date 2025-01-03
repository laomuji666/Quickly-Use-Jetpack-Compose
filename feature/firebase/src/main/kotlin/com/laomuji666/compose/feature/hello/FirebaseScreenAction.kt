package com.laomuji666.compose.feature.hello

sealed interface FirebaseScreenAction {
    data object OnClickLogEvent : FirebaseScreenAction
    data object UpdatePushToken : FirebaseScreenAction
    data object TestCrashlytics : FirebaseScreenAction
}