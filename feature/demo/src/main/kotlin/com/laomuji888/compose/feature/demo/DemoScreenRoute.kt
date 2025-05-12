package com.laomuji888.compose.feature.demo

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object DemoScreenRoute{
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
        onLanguageClick:()->Unit,
    ){
        composable<DemoScreenRoute>{
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
                onLanguageClick = onLanguageClick,
            )
        }
    }
}