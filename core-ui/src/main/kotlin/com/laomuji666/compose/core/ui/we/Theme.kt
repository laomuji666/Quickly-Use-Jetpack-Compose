package com.laomuji666.compose.core.ui.we

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
internal fun DefaultWeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val weColorScheme = if(darkTheme) DarkWeColorScheme else LightWeColorScheme
    WeTheme(
        weColorScheme = weColorScheme
    ){
        content()
    }
}