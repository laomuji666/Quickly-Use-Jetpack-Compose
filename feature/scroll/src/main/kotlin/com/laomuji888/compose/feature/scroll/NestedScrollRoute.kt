package com.laomuji888.compose.feature.scroll

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji888.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object RouteNestedScrollConnectionScreen

fun NavHostController.navigateToNestedScrollConnectionScreen(navOptions: NavOptions = navOptionsPushBack()){
    navigate(RouteNestedScrollConnectionScreen, navOptions)
}

fun NavGraphBuilder.composeNestedScrollConnectionScreen(){
    composable<RouteNestedScrollConnectionScreen>{
        NestedScrollConnectionScreen()
    }
}

@Serializable
data object RouteNestedScrollDispatcherScreen

fun NavHostController.navigateToNestedScrollDispatcherScreen(navOptions: NavOptions = navOptionsPushBack()){
    navigate(RouteNestedScrollDispatcherScreen, navOptions)
}

fun NavGraphBuilder.composeNestedScrollDispatcherScreen(){
    composable<RouteNestedScrollDispatcherScreen>{
        NestedScrollDispatcherScreen()
    }
}

