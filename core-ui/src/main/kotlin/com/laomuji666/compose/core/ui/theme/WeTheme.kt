package com.laomuji666.compose.core.ui.theme

import androidx.compose.runtime.Composable

@Composable
fun WeTheme(
    weColorScheme: WeColorScheme,
    weTypography: WeTypography,
    content: @Composable () -> Unit
){
    content()
}