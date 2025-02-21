package com.laomuji666.compose.feature.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object RouteAiChatScreen

fun NavHostController.navigateToAiChatScreen(navOptions: NavOptions = navOptionsPushBack()){
    navigate(RouteAiChatScreen, navOptions)
}

fun NavGraphBuilder.composeAiChatScreen(
    onContactClick: (account:Long)->Unit
){
    composable<RouteAiChatScreen>{
        AiChatScreen(
            onContactClick = {
                onContactClick(
                    it.account
                )
            }
        )
    }
}