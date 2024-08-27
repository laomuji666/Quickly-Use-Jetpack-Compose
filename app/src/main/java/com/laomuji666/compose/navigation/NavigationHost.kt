package com.laomuji666.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.laomuji666.compose.core.ui.safePopBackStack
import com.laomuji666.compose.feature.chat.composeAiChatScreen
import com.laomuji666.compose.feature.chat.navigateToAiChatScreen
import com.laomuji666.compose.feature.hello.HELLO_SCREEN
import com.laomuji666.compose.feature.hello.composeFirebaseScreen
import com.laomuji666.compose.feature.hello.composeHelloScreen
import com.laomuji666.compose.feature.hello.composeHttpScreen
import com.laomuji666.compose.feature.hello.navigateToFirebaseScreen
import com.laomuji666.compose.feature.hello.navigateToHttpScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination:String = HELLO_SCREEN,
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

        composeAiChatScreen()
    }
}