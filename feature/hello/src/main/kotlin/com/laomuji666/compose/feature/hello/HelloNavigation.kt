package com.laomuji666.compose.feature.hello

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HELLO_SCREEN = "HELLO_SCREEN"

fun NavGraphBuilder.composeHelloScreen(){
    composable(route = HELLO_SCREEN){
        HelloScreen()
    }
}