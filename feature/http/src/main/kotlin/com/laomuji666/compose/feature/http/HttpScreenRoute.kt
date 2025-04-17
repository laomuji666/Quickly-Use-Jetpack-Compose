package com.laomuji666.compose.feature.http

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object HttpScreenRoute{
    fun NavHostController.navigateToHttpScreen(navOptions: NavOptions = navOptionsPushBack()){
        navigate(HttpScreenRoute, navOptions)
    }

    fun NavGraphBuilder.composeHttpScreen(
        onBackClick:()->Unit
    ){
        composable<HttpScreenRoute>{
            HttpScreen(
                onBackClick = onBackClick
            )
        }
    }
}