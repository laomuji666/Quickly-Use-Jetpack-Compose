package com.laomuji666.compose.feature.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack


const val AI_CHAT_SCREEN = "AI_CHAT_SCREEN"

fun NavHostController.navigateToAiChatScreen(navOptions: NavOptions = navOptionsPushBack(this)){
    navigate(AI_CHAT_SCREEN, navOptions)
}

fun NavGraphBuilder.composeAiChatScreen(
    onContactClick: (account:Long, nickname:String)->Unit
){
    composable(route = AI_CHAT_SCREEN){
        AiChatScreen(
            onContactClick = {
                onContactClick(
                    it.account,
                    it.nickname
                )
            }
        )
    }
}