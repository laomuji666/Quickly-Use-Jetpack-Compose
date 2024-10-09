package com.laomuji666.compose.feature.chat.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack

private const val CHAT_SCREEN = "CHAT_SCREEN"
internal const val ACCOUNT = "ACCOUNT"
private const val CHAT_SCREEN_ROUTE = "$CHAT_SCREEN/{$ACCOUNT}"

fun NavHostController.navigateToChatScreen(account: Long, navOptions: NavOptions? = navOptionsPushBack(this)){
    return navigate("$CHAT_SCREEN/$account", navOptions)
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