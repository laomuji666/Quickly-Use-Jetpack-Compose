package com.laomuji888.compose.feature.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji888.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object AiChatScreenRoute {
    sealed interface Graph {
        data class Chat(val account: Long) : Graph
    }

    fun NavHostController.navigateToAiChatScreen(navOptions: NavOptions = navOptionsPushBack()) {
        navigate(AiChatScreenRoute, navOptions)
    }

    fun NavGraphBuilder.composeAiChatScreen(
        navigateToGraph: (Graph) -> Unit,
    ) {
        composable<AiChatScreenRoute> {
            AiChatScreen(
                navigateToGraph = navigateToGraph
            )
        }
    }
}