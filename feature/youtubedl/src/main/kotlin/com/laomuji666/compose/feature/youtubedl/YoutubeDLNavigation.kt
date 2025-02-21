package com.laomuji666.compose.feature.youtubedl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object RouteYoutubeDLScreen

fun NavHostController.navigateToYoutubeDLScreen(navOptions: NavOptions = navOptionsPushBack()){
    navigate(RouteYoutubeDLScreen, navOptions)
}

fun NavGraphBuilder.composeYoutubeDLScreen(){
    composable<RouteYoutubeDLScreen>{
        YoutubeDLScreen()
    }
}