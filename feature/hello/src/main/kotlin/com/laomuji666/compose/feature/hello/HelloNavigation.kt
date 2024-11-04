package com.laomuji666.compose.feature.hello

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object RouteHelloScreen

fun NavGraphBuilder.composeHelloScreen(
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit,
    onAiChatClick:()->Unit
){
    composable<RouteHelloScreen>{
        HelloScreen(
            onFirebaseClick = onFirebaseClick,
            onHttpClick = onHttpClick,
            onAiChatClick = onAiChatClick
        )
    }
}