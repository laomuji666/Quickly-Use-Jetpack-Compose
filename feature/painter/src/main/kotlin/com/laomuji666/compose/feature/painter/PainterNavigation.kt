package com.laomuji666.compose.feature.painter

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object RoutePainterScreen

fun NavHostController.navigateToPainterScreen(navOptions: NavOptions = navOptionsPushBack(this)){
    navigate(RoutePainterScreen, navOptions)
}

fun NavGraphBuilder.composePainterScreen(){
    composable<RoutePainterScreen>{
        PainterScreen()
    }
}