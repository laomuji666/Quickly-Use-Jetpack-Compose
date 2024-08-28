package com.laomuji666.compose.core.ui.we2

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class WeColorScheme (
    val background: Color,
    val topNavigationBarBackgroundColor: Color,
    val fontColor90: Color,
    val primaryButton: Color,
    val onPrimaryButton: Color,
    val secondaryButton: Color,
    val onSecondaryButton: Color,
    val tertiaryButton: Color,
    val onTertiaryButton: Color,
    val outline: Color
)

val LightWeColorScheme = WeColorScheme(
    background = WeColors.White100,
    topNavigationBarBackgroundColor = WeColors.White100,
    fontColor90  = WeColors.FontColorLight90Alpha,
    primaryButton = WeColors.Brand100,
    onPrimaryButton = WeColors.White100,
    secondaryButton = WeColors.White97,
    onSecondaryButton = WeColors.Brand100,
    tertiaryButton = WeColors.White97,
    onTertiaryButton = WeColors.Brand100,
    outline = WeColors.White97
)

val DarkWeColorScheme = WeColorScheme(
    background = WeColors.Dark100,
    topNavigationBarBackgroundColor = WeColors.Dark20,
    fontColor90 = WeColors.FontColorDark90Alpha,
    primaryButton = WeColors.Brand100,
    onPrimaryButton = WeColors.Dark100,
    secondaryButton = WeColors.White97,
    onSecondaryButton = WeColors.Brand100,
    tertiaryButton = WeColors.FontColorDark15Alpha,
    onTertiaryButton = WeColors.Brand100,
    outline = WeColors.Dark20
)

internal val LocalWeColorScheme = staticCompositionLocalOf { LightWeColorScheme }