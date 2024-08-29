package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class WeColorScheme (
    val background: Color,
    val topNavigationBarBackground: Color,
    val fontColor90: Color,
    val fontColor50: Color,
    val primaryButton: Color,
    val onPrimaryButton: Color,
    val secondaryButton: Color,
    val onSecondaryButton: Color,
    val tertiaryButton: Color,
    val onTertiaryButton: Color,
    val outline: Color,
    val error: Color,
    val navigationBarBackground: Color,
    val navigationBarSelect: Color,
    val navigationBarUnSelect: Color,
    val navigationBarOutline: Color
)

val LightWeColorScheme = WeColorScheme(
    background = WeColors.Color_FFFFFFFF,
    topNavigationBarBackground = WeColors.Color_FFEDEDED,
    fontColor90  = WeColors.Color_FF000000_90,
    fontColor50 = WeColors.Color_FF000000_50,
    primaryButton = WeColors.Color_FF07C160,
    onPrimaryButton = WeColors.Color_FFFFFFFF,
    secondaryButton = WeColors.Color_FFF7F7F7,
    onSecondaryButton = WeColors.Color_FF07C160,
    tertiaryButton = WeColors.Color_FFF7F7F7,
    onTertiaryButton = WeColors.Color_FF07C160,
    outline = WeColors.Color_FFEDEDED,
    error = WeColors.Color_FFFA5151,
    navigationBarBackground = WeColors.Color_FFF7F7F7,
    navigationBarSelect = WeColors.Color_FF07C160,
    navigationBarUnSelect = WeColors.Color_FF000000_90,
    navigationBarOutline = WeColors.Color_FF000000_05
)

val DarkWeColorScheme = WeColorScheme(
    background = WeColors.Color_FF000000,
    topNavigationBarBackground = WeColors.Color_FF101010,
    fontColor90 = WeColors.Color_FFFFFFFF_90,
    fontColor50 = WeColors.Color_FFFFFFFF_50,
    primaryButton = WeColors.Color_FF07C160,
    onPrimaryButton = WeColors.Color_FF000000,
    secondaryButton = WeColors.Color_FFF7F7F7,
    onSecondaryButton = WeColors.Color_FF07C160,
    tertiaryButton = WeColors.Color_FFFFFFFF_15,
    onTertiaryButton = WeColors.Color_FF07C160,
    outline = WeColors.Color_FF101010,
    error = WeColors.Color_FFFA5151,
    navigationBarBackground = WeColors.Color_FF1E1E1E,
    navigationBarSelect = WeColors.Color_FF07C160,
    navigationBarUnSelect = WeColors.Color_FFFFFFFF_90,
    navigationBarOutline = WeColors.Color_FFFFFFFF_15
)

internal val LocalWeColorScheme = staticCompositionLocalOf { LightWeColorScheme }