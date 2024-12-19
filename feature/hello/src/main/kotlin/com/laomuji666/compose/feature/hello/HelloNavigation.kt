package com.laomuji666.compose.feature.hello

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object RouteHelloScreen

fun NavGraphBuilder.composeHelloScreen(
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit,
    onAiChatClick:()->Unit,
    onDateClick:()->Unit,
    onNestedScrollConnectionScreenClick:()->Unit,
    onNestedScrollDispatcherScreenClick:()->Unit,
    onBiometricScreenClick:()->Unit,
    onPainterScreenClick:()->Unit,
){
    composable<RouteHelloScreen>{
        HelloScreen(
            onFirebaseClick = onFirebaseClick,
            onHttpClick = onHttpClick,
            onAiChatClick = onAiChatClick,
            onDateClick = onDateClick,
            onNestedScrollConnectionScreenClick = onNestedScrollConnectionScreenClick,
            onNestedScrollDispatcherScreenClick = onNestedScrollDispatcherScreenClick,
            onBiometricScreenClick = onBiometricScreenClick,
            onPainterScreenClick = onPainterScreenClick,
        )
    }
}