package com.laomuji666.compose.feature.hello

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object RouteFirebaseScreen

fun NavHostController.navigateToFirebaseScreen(navOptions: NavOptions = navOptionsPushBack(this)){
    navigate(RouteFirebaseScreen, navOptions)
}

fun NavGraphBuilder.composeFirebaseScreen(
    onBackClick: ()->Unit
){
    composable<RouteFirebaseScreen>{
        FirebaseScreen(
            onBackClick = onBackClick
        )
    }
}