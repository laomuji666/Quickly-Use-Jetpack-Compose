package com.laomuji666.compose.feature.hello

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack

const val FIREBASE_SCREEN = "FIREBASE_SCREEN"

fun NavHostController.navigateToFirebaseScreen(navOptions: NavOptions = navOptionsPushBack(this)){
    navigate(FIREBASE_SCREEN, navOptions)
}

fun NavGraphBuilder.composeFirebaseScreen(){
    composable(route = FIREBASE_SCREEN){
        FirebaseScreen()
    }
}