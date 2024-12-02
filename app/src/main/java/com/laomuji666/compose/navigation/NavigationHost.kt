package com.laomuji666.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.laomuji666.compose.core.ui.safePopBackStack
import com.laomuji666.compose.feature.chat.chat.composeChatScreen
import com.laomuji666.compose.feature.chat.chat.navigateToChatScreen
import com.laomuji666.compose.feature.chat.composeAiChatScreen
import com.laomuji666.compose.feature.chat.navigateToAiChatScreen
import com.laomuji666.compose.feature.date.composeDateScreen
import com.laomuji666.compose.feature.date.navigateToDateScreen
import com.laomuji666.compose.feature.hello.RouteHelloScreen
import com.laomuji666.compose.feature.hello.composeFirebaseScreen
import com.laomuji666.compose.feature.hello.composeHelloScreen
import com.laomuji666.compose.feature.hello.composeHttpScreen
import com.laomuji666.compose.feature.hello.navigateToFirebaseScreen
import com.laomuji666.compose.feature.hello.navigateToHttpScreen
import com.laomuji666.compose.feature.scroll.composeNestedScrollConnectionScreen
import com.laomuji666.compose.feature.scroll.composeNestedScrollDispatcherScreen
import com.laomuji666.compose.feature.scroll.navigateToNestedScrollConnectionScreen
import com.laomuji666.compose.feature.scroll.navigateToNestedScrollDispatcherScreen

/**
 * 导航控制器
 * 不同的feature之间的跳转
 */
@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination:Any = RouteHelloScreen,
    animTime:Int = 300
){
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(animTime)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(animTime)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(animTime)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(animTime)
            )
        }
    ){
        composeHelloScreen(
            onFirebaseClick = {
                navHostController.navigateToFirebaseScreen()
            },
            onHttpClick = {
                navHostController.navigateToHttpScreen()
            },
            onAiChatClick = {
                navHostController.navigateToAiChatScreen()
            },
            onDateClick = {
                navHostController.navigateToDateScreen()
            },
            onNestedScrollConnectionScreenClick = {
                navHostController.navigateToNestedScrollConnectionScreen()
            },
            onNestedScrollDispatcherScreenClick = {
                navHostController.navigateToNestedScrollDispatcherScreen()
            }
        )

        composeFirebaseScreen(
            onBackClick = {
                navHostController.safePopBackStack()
            }
        )

        composeHttpScreen(
            onBackClick = {
                navHostController.safePopBackStack()
            }
        )

        composeAiChatScreen(
            onContactClick = { account ->
                navHostController.navigateToChatScreen(
                    account = account
                )
            }
        )

        composeChatScreen(
            onBackClick = {
                navHostController.safePopBackStack()
            }
        )

        composeDateScreen()

        composeNestedScrollConnectionScreen()
        composeNestedScrollDispatcherScreen()
    }
}