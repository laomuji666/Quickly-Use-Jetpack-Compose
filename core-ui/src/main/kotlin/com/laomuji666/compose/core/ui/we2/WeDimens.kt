package com.laomuji666.compose.core.ui.we2

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WeDimens(
    val topNavigationBarHeight:Dp,
    val topNavigationBarPaddingHorizontal:Dp,
    val topNavigationBarActionWidth:Dp,
    val topNavigationBarIconSize:Dp,
    val topNavigationBarActionPaddingWidth:Dp,
)

val DefaultWeDimens = WeDimens(
    topNavigationBarHeight = 56.dp,
    topNavigationBarPaddingHorizontal = 10.dp,
    topNavigationBarActionWidth = 90.dp,
    topNavigationBarIconSize = 24.dp,
    topNavigationBarActionPaddingWidth = 16.dp
)

internal val LocalWeDimens = staticCompositionLocalOf { DefaultWeDimens }