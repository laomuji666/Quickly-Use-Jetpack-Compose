package com.laomuji666.compose.core.ui.we2

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class WeColorScheme (
    val topNavigationBarBackgroundColor: Color,
    val fontColor90: Color,
)

val LightWeColorScheme = WeColorScheme(
    topNavigationBarBackgroundColor = WeColors.White100,
    fontColor90  = WeColors.FontColorLight90
)

val DarkWeColorScheme = WeColorScheme(
    topNavigationBarBackgroundColor = WeColors.Dark20,
    fontColor90 = WeColors.FontColorDark90
)

internal val LocalWeColorScheme = staticCompositionLocalOf { LightWeColorScheme }