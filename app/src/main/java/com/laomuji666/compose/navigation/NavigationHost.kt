package com.laomuji666.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.laomuji666.compose.core.ui.safePopBackStack
import com.laomuji666.compose.feature.biometric.BiometricScreenRoute.composeBiometricScreen
import com.laomuji666.compose.feature.biometric.BiometricScreenRoute.navigateToBiometricScreen
import com.laomuji666.compose.feature.chat.AiChatScreenRoute.composeAiChatScreen
import com.laomuji666.compose.feature.chat.AiChatScreenRoute.navigateToAiChatScreen
import com.laomuji666.compose.feature.chat.chat.ChatScreenRoute.Companion.composeChatScreen
import com.laomuji666.compose.feature.chat.chat.ChatScreenRoute.Companion.navigateToChatScreen
import com.laomuji666.compose.feature.date.DateScreenRoute.composeDateScreen
import com.laomuji666.compose.feature.date.DateScreenRoute.navigateToDateScreen
import com.laomuji666.compose.feature.demo.DemoScreenRoute
import com.laomuji666.compose.feature.demo.DemoScreenRoute.composeDemoScreen
import com.laomuji666.compose.feature.firebase.FirebaseScreenRoute.composeFirebaseScreen
import com.laomuji666.compose.feature.firebase.FirebaseScreenRoute.navigateToFirebaseScreen
import com.laomuji666.compose.feature.http.HttpScreenRoute.composeHttpScreen
import com.laomuji666.compose.feature.http.HttpScreenRoute.navigateToHttpScreen
import com.laomuji666.compose.feature.painter.PainterScreenRoute.composePainterScreen
import com.laomuji666.compose.feature.painter.PainterScreenRoute.navigateToPainterScreen
import com.laomuji666.compose.feature.scroll.composeNestedScrollConnectionScreen
import com.laomuji666.compose.feature.scroll.composeNestedScrollDispatcherScreen
import com.laomuji666.compose.feature.scroll.navigateToNestedScrollConnectionScreen
import com.laomuji666.compose.feature.scroll.navigateToNestedScrollDispatcherScreen
import com.laomuji666.compose.feature.webview.WebViewScreenRoute.Companion.composeWebViewScreen
import com.laomuji666.compose.feature.webview.WebViewScreenRoute.Companion.navigateToWebViewScreen
import com.laomuji666.compose.feature.youtubedl.YoutubeDLScreenRoute.composeYoutubeDLScreen
import com.laomuji666.compose.feature.youtubedl.YoutubeDLScreenRoute.navigateToYoutubeDLScreen

/**
 * 导航控制器
 * 不同的feature之间的跳转
 */
@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination:Any = DemoScreenRoute,
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
        composeDemoScreen(
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
            },
            onBiometricScreenClick = {
                navHostController.navigateToBiometricScreen()
            },
            onPainterScreenClick = {
                navHostController.navigateToPainterScreen()
            },
            onYoutubeDLClick = {
                navHostController.navigateToYoutubeDLScreen()
            },
            onWebViewClick = {
                navHostController.navigateToWebViewScreen("https://www.google.com/")
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

        composeBiometricScreen()

        composePainterScreen()

        composeYoutubeDLScreen()

        composeWebViewScreen(
            onBackClick = {
                navHostController.safePopBackStack()
            },
            onOpenNewWindow = { url ->
                navHostController.navigateToWebViewScreen(url)
            }
        )
    }
}