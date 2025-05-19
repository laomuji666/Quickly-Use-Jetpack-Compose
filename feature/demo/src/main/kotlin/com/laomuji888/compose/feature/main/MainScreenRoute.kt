package com.laomuji888.compose.feature.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji888.compose.core.ui.navOptionsRemoveAll
import kotlinx.serialization.Serializable

@Serializable
data object MainScreenRoute {
    fun NavHostController.navigateToMainScreen(
        route: MainScreenRoute,
        navOptions: NavOptions = navOptionsRemoveAll()
    ) {
        navigate(route = route, navOptions = navOptions)
    }

    fun NavGraphBuilder.composeMainScreen(
        onFirebaseClick: () -> Unit,
        onHttpClick: () -> Unit,
        onAiChatClick: () -> Unit,
        onDateClick: () -> Unit,
        onNestedScrollConnectionScreenClick: () -> Unit,
        onNestedScrollDispatcherScreenClick: () -> Unit,
        onBiometricScreenClick: () -> Unit,
        onPainterScreenClick: () -> Unit,
        onYoutubeDLClick: () -> Unit,
        onWebViewClick: () -> Unit,
        onLanguageClick: () -> Unit,
    ) {
        composable<MainScreenRoute> {
            MainScreen(
                onFirebaseClick = onFirebaseClick,
                onHttpClick = onHttpClick,
                onAiChatClick = onAiChatClick,
                onDateClick = onDateClick,
                onNestedScrollConnectionScreenClick = onNestedScrollConnectionScreenClick,
                onNestedScrollDispatcherScreenClick = onNestedScrollDispatcherScreenClick,
                onBiometricScreenClick = onBiometricScreenClick,
                onPainterScreenClick = onPainterScreenClick,
                onYoutubeDLClick = onYoutubeDLClick,
                onWebViewClick = onWebViewClick,
                onLanguageClick = onLanguageClick,
            )
        }
    }
}