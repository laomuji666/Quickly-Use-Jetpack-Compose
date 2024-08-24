package com.laomuji666.compose.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.darkWeColorScheme
import com.laomuji666.compose.core.ui.we.defaultTypography
import com.laomuji666.compose.core.ui.we.lightWeColorScheme

@Composable
fun QuicklyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val weColorScheme = if(darkTheme) darkWeColorScheme else lightWeColorScheme
    WeTheme(
        weColorScheme = weColorScheme,
        weTypography = defaultTypography
    ){
        content()
    }
}