package com.laomuji666.compose.feature.language

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object LanguageScreenRoute {
    fun NavHostController.navigateToLanguageScreen(
        navOptions: NavOptions = navOptionsPushBack()
    ) {
        navigate(route = LanguageScreenRoute, navOptions = navOptions)
    }

    fun NavGraphBuilder.composeLanguageScreen(
        onBackClick: () -> Unit,
    ) {
        composable<LanguageScreenRoute> {
            LanguageScreen(
                onBackClick = onBackClick,
            )
        }
    }
}