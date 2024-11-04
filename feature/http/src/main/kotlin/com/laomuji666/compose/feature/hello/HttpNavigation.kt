package com.laomuji666.compose.feature.hello

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
private data object RouteHttpScreen

fun NavHostController.navigateToHttpScreen(navOptions: NavOptions = navOptionsPushBack(this)){
    navigate(RouteHttpScreen, navOptions)
}

fun NavGraphBuilder.composeHttpScreen(
    onBackClick:()->Unit
){
    composable<RouteHttpScreen>{
        HttpScreen(
            onBackClick = onBackClick
        )
    }
}