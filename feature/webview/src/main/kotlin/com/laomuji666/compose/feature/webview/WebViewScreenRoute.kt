package com.laomuji666.compose.feature.webview

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data class WebViewScreenRoute(
    val url:String
)

fun NavHostController.navigateToWebViewScreen(url: String, navOptions: NavOptions = navOptionsPushBack()){
    navigate(WebViewScreenRoute(url = url), navOptions)
}

fun NavGraphBuilder.composeWebViewScreen(
    onBackClick: () -> Unit,
    onOpenNewWindow: (String)->Unit,
){
    composable<WebViewScreenRoute>{
        WebViewScreen(
            onBackClick = onBackClick,
            onOpenNewWindow = onOpenNewWindow,
        )
    }
}