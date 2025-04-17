package com.laomuji666.compose.feature.chat.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

const val CHAT_SCREEN_DEEP_LINK = "laomuji://compose.laomuji666.com/chat"

@Serializable
data class ChatScreenRoute(
    val account: Long
){
    companion object{
        fun NavHostController.navigateToChatScreen(account: Long, navOptions: NavOptions? = navOptionsPushBack()){
            return navigate(ChatScreenRoute(account), navOptions)
        }

        fun NavGraphBuilder.composeChatScreen(
            onBackClick: ()->Unit
        ){
            composable<ChatScreenRoute>(
                deepLinks = listOf(
                    navDeepLink<ChatScreenRoute>(basePath = CHAT_SCREEN_DEEP_LINK)
                )
            ){
                ChatScreen(
                    onBackClick = onBackClick
                )
            }
        }
    }
}

