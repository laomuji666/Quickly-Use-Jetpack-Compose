package com.laomuji888.compose.feature.youtubedl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji888.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object YoutubeDLScreenRoute{
    fun NavHostController.navigateToYoutubeDLScreen(navOptions: NavOptions = navOptionsPushBack()){
        navigate(YoutubeDLScreenRoute, navOptions)
    }

    fun NavGraphBuilder.composeYoutubeDLScreen(){
        composable<YoutubeDLScreenRoute>{
            YoutubeDLScreen()
        }
    }
}