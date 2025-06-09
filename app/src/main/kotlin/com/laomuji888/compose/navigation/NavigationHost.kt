package com.laomuji888.compose.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.laomuji888.compose.WebViewActivity
import com.laomuji888.compose.core.ui.safePopBackStack
import com.laomuji888.compose.core.ui.screen.SlideNavigation
import com.laomuji888.compose.feature.biometric.BiometricScreenRoute.composeBiometricScreen
import com.laomuji888.compose.feature.biometric.BiometricScreenRoute.navigateToBiometricScreen
import com.laomuji888.compose.feature.chat.AiChatScreenRoute
import com.laomuji888.compose.feature.chat.AiChatScreenRoute.composeAiChatScreen
import com.laomuji888.compose.feature.chat.AiChatScreenRoute.navigateToAiChatScreen
import com.laomuji888.compose.feature.chat.chat.ChatScreenRoute
import com.laomuji888.compose.feature.chat.chat.ChatScreenRoute.Companion.composeChatScreen
import com.laomuji888.compose.feature.chat.chat.ChatScreenRoute.Companion.navigateToChatScreen
import com.laomuji888.compose.feature.date.DateScreenRoute.composeDateScreen
import com.laomuji888.compose.feature.date.DateScreenRoute.navigateToDateScreen
import com.laomuji888.compose.feature.firebase.FirebaseScreenRoute.composeFirebaseScreen
import com.laomuji888.compose.feature.firebase.FirebaseScreenRoute.navigateToFirebaseScreen
import com.laomuji888.compose.feature.http.HttpScreenRoute.composeHttpScreen
import com.laomuji888.compose.feature.http.HttpScreenRoute.navigateToHttpScreen
import com.laomuji888.compose.feature.language.LanguageScreenRoute
import com.laomuji888.compose.feature.language.LanguageScreenRoute.composeLanguageScreen
import com.laomuji888.compose.feature.language.LanguageScreenRoute.navigateToLanguageScreen
import com.laomuji888.compose.feature.main.MainScreenRoute
import com.laomuji888.compose.feature.main.MainScreenRoute.composeMainScreen
import com.laomuji888.compose.feature.painter.PainterScreenRoute.composePainterScreen
import com.laomuji888.compose.feature.painter.PainterScreenRoute.navigateToPainterScreen
import com.laomuji888.compose.feature.scroll.composeNestedScrollConnectionScreen
import com.laomuji888.compose.feature.scroll.composeNestedScrollDispatcherScreen
import com.laomuji888.compose.feature.scroll.navigateToNestedScrollConnectionScreen
import com.laomuji888.compose.feature.scroll.navigateToNestedScrollDispatcherScreen
import com.laomuji888.compose.feature.youtubedl.YoutubeDLScreenRoute.composeYoutubeDLScreen
import com.laomuji888.compose.feature.youtubedl.YoutubeDLScreenRoute.navigateToYoutubeDLScreen

/**
 * 导航控制器
 * 不同的feature之间的跳转
 */
@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: Any = MainScreenRoute,
) {
    val activity = LocalActivity.current
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        enterTransition = SlideNavigation.enterTransition,
        exitTransition = SlideNavigation.exitTransition,
        popEnterTransition = SlideNavigation.popEnterTransition,
        popExitTransition = SlideNavigation.popExitTransition
    ) {
        composeMainScreen(
            navigateToGraph = {
                when (it) {
                    MainScreenRoute.Graph.AiChat -> navHostController.navigateToAiChatScreen()
                    MainScreenRoute.Graph.Biometric -> navHostController.navigateToBiometricScreen()
                    MainScreenRoute.Graph.Date -> navHostController.navigateToDateScreen()
                    MainScreenRoute.Graph.Firebase -> navHostController.navigateToFirebaseScreen()
                    MainScreenRoute.Graph.Http -> navHostController.navigateToHttpScreen()
                    MainScreenRoute.Graph.Language -> navHostController.navigateToLanguageScreen()
                    MainScreenRoute.Graph.NestedScrollConnection -> navHostController.navigateToNestedScrollConnectionScreen()
                    MainScreenRoute.Graph.NestedScrollDispatcher -> navHostController.navigateToNestedScrollDispatcherScreen()
                    MainScreenRoute.Graph.Painter -> navHostController.navigateToPainterScreen()
                    MainScreenRoute.Graph.YoutubeDL -> navHostController.navigateToYoutubeDLScreen()
                    MainScreenRoute.Graph.WebView -> activity?.let {
                        WebViewActivity.openWebViewActivity("https://www.google.com/", activity)
                    }
                }
            },
        )

        composeFirebaseScreen()

        composeHttpScreen()

        composeAiChatScreen(
            navigateToGraph = {
                when (it) {
                    is AiChatScreenRoute.Graph.Chat -> {
                        navHostController.navigateToChatScreen(
                            account = it.account
                        )
                    }
                }
            },
        )

        composeChatScreen(
            navigateToGraph = {
                when (it) {
                    ChatScreenRoute.Graph.Back -> navHostController.safePopBackStack()
                }
            },
        )

        composeDateScreen()

        composeNestedScrollConnectionScreen()
        composeNestedScrollDispatcherScreen()

        composeBiometricScreen()

        composePainterScreen()

        composeYoutubeDLScreen()

        composeLanguageScreen(
            navigateToGraph = {
                when (it) {
                    LanguageScreenRoute.Graph.Back -> navHostController.safePopBackStack()
                }
            },
        )
    }
}