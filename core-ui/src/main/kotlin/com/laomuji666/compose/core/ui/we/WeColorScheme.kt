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
    val disableButton: Color,
    val onDisableButton: Color,
    val wrongButton: Color,
    val onWrongButton: Color,

    val outline: Color,
    val error: Color,

    val navigationBarBackground: Color,
    val navigationBarSelect: Color,
    val navigationBarUnSelect: Color,
    val navigationBarOutline: Color,
    val toastBackgroundColor: Color,
    val onToastBackgroundColor: Color,

    val switchSelectBackground: Color,
    val switchUnSelectBackground: Color,

    val tableRowBackground: Color
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
    disableButton = WeColors.Color_FFF7F7F7,
    onDisableButton = WeColors.Color_FF000000_10,
    wrongButton = WeColors.Color_FFF7F7F7,
    onWrongButton = WeColors.Color_FFFA5151,



    outline = WeColors.Color_FF000000_05,
    error = WeColors.Color_FFFA5151,

    navigationBarBackground = WeColors.Color_FFF7F7F7,
    navigationBarSelect = WeColors.Color_FF07C160,
    navigationBarUnSelect = WeColors.Color_FF000000_90,
    navigationBarOutline = WeColors.Color_FF000000_05,
    toastBackgroundColor = WeColors.Color_FF4C4C4C_90,
    onToastBackgroundColor = WeColors.Color_FFFFFFFF_90,

    switchSelectBackground = WeColors.Color_FF07C160,
    switchUnSelectBackground = WeColors.Color_FF000000_05,

    tableRowBackground = WeColors.Color_FFFFFFFF
)

val DarkWeColorScheme = WeColorScheme(
    background = WeColors.Color_FF000000,
    topNavigationBarBackground = WeColors.Color_FF111111,

    fontColor90 = WeColors.Color_FFFFFFFF_90,
    fontColor50 = WeColors.Color_FFFFFFFF_50,

    primaryButton = WeColors.Color_FF07C160,
    onPrimaryButton = WeColors.Color_FFFFFFFF,
    secondaryButton = WeColors.Color_FFFFFFFF_15,
    onSecondaryButton = WeColors.Color_FF07C160,
    disableButton = WeColors.Color_FFFFFFFF_15,
    onDisableButton = WeColors.Color_FFFFFFFF_15,
    wrongButton = WeColors.Color_FFFFFFFF_15,
    onWrongButton = WeColors.Color_FFFA5151,


    outline = WeColors.Color_FFFFFFFF_05,
    error = WeColors.Color_FFFA5151,


    navigationBarBackground = WeColors.Color_FF1E1E1E,
    navigationBarSelect = WeColors.Color_FF07C160,
    navigationBarUnSelect = WeColors.Color_FFFFFFFF_90,
    navigationBarOutline = WeColors.Color_FFFFFFFF_15,
    toastBackgroundColor = WeColors.Color_FF4C4C4C_90,
    onToastBackgroundColor = WeColors.Color_FFFFFFFF_90,

    switchSelectBackground = WeColors.Color_FF07C160,
    switchUnSelectBackground = WeColors.Color_FFFFFFFF_15,

    tableRowBackground = WeColors.Color_FF191919
)

internal val LocalWeColorScheme = staticCompositionLocalOf { LightWeColorScheme }