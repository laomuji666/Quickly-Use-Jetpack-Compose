package com.laomuji666.compose.feature.biometric

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.laomuji666.compose.core.ui.navOptionsPushBack
import kotlinx.serialization.Serializable

@Serializable
data object BiometricScreenRoute{
    fun NavHostController.navigateToBiometricScreen(
        navOptions: NavOptions = navOptionsPushBack()
    ){
        navigate(route = BiometricScreenRoute, navOptions = navOptions)
    }

    fun NavGraphBuilder.composeBiometricScreen(
    ){
        composable<BiometricScreenRoute>{
            BiometricScreen()
        }
    }
}