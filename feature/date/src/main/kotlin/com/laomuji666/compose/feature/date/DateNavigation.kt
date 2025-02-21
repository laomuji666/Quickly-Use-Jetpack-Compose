package com.laomuji666.compose.feature.date

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object RouteDateScreen


fun NavHostController.navigateToDateScreen(navOptions: NavOptions = navOptionsPushBack()){
    navigate(RouteDateScreen, navOptions)
}

fun NavGraphBuilder.composeDateScreen(){
    composable<RouteDateScreen>{
        DateScreen()
    }
}