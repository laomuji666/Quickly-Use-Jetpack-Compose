package com.laomuji666.compose.feature.template

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data class TemplateScreenRoute(
    val title: String,
    val text: String,
){
    companion object{
        fun NavHostController.navigateToTemplateScreen(
            route: TemplateScreenRoute,
            navOptions: NavOptions = navOptionsPushBack()
        ){
            navigate(route = route, navOptions = navOptions)
        }

        fun NavGraphBuilder.composeTemplateScreen(
            onBackClick: () -> Unit,
            onButtonClick: (String) -> Unit,
        ){
            composable<TemplateScreenRoute>{
                TemplateScreen(
                    onBackClick = onBackClick,
                    onButtonClick = onButtonClick,
                )
            }
        }
    }
}