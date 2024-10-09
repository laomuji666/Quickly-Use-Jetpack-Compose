package com.laomuji666.compose.feature.chat.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack

private const val CHAT_SCREEN = "CHAT_SCREEN"
internal const val ACCOUNT = "ACCOUNT"
internal const val NICKNAME = "NICKNAME"
private const val CHAT_SCREEN_ROUTE = "$CHAT_SCREEN/{$ACCOUNT}/{$NICKNAME}"

fun NavHostController.navigateToChatScreen(account: Long, nickname: String, navOptions: NavOptions? = navOptionsPushBack(this)){
    return navigate("$CHAT_SCREEN/$account/$nickname", navOptions)
}

fun NavGraphBuilder.composeChatScreen(
    onBackClick: ()->Unit
){
    composable(route = CHAT_SCREEN_ROUTE){
        ChatScreen(
            onBackClick = onBackClick
        )
    }
}