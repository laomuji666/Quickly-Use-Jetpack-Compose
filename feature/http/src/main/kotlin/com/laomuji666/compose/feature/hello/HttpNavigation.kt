package com.laomuji666.compose.feature.hello

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack


const val HTTP_SCREEN = "HTTP_SCREEN"

fun NavHostController.navigateToHttpScreen(navOptions: NavOptions = navOptionsPushBack(this)){
    navigate(HTTP_SCREEN, navOptions)
}

fun NavGraphBuilder.composeHttpScreen(){
    composable(route = HTTP_SCREEN){
        HttpScreen()
    }
}