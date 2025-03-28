package com.laomuji666.compose.feature.demo

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object RouteDemoScreen

fun NavGraphBuilder.composeDemoScreen(
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit,
    onAiChatClick:()->Unit,
    onDateClick:()->Unit,
    onNestedScrollConnectionScreenClick:()->Unit,
    onNestedScrollDispatcherScreenClick:()->Unit,
    onBiometricScreenClick:()->Unit,
    onPainterScreenClick:()->Unit,
    onYoutubeDLClick:()->Unit,
    onWebViewClick:()->Unit,
){
    composable<RouteDemoScreen>{
        DemoScreen(
            onFirebaseClick = onFirebaseClick,
            onHttpClick = onHttpClick,
            onAiChatClick = onAiChatClick,
            onDateClick = onDateClick,
            onNestedScrollConnectionScreenClick = onNestedScrollConnectionScreenClick,
            onNestedScrollDispatcherScreenClick = onNestedScrollDispatcherScreenClick,
            onBiometricScreenClick = onBiometricScreenClick,
            onPainterScreenClick = onPainterScreenClick,
            onYoutubeDLClick = onYoutubeDLClick,
            onWebViewClick = onWebViewClick,
        )
    }
}