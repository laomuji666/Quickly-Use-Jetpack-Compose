package com.laomuji666.compose.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun QuicklyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val weColorScheme = if(darkTheme) darkWeColorScheme else lightWeColorScheme
    val weTypography = if(darkTheme) darkWeTypography else lightWeTypography

    WeTheme(
        weColorScheme = weColorScheme,
        weTypography = weTypography
    ){
        content()
    }
}