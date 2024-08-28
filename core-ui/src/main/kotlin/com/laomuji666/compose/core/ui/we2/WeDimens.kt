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

    val bigButtonWidth:Dp,
    val bigButtonHeight:Dp,
    val bigButtonRoundedCornerDp:Dp,
    val smallButtonWidth:Dp,
    val smallButtonHeight:Dp,
    val smallButtonRoundedCornerDp:Dp,

    val listSingleRowHeight:Dp,
    val listDoubleRowHeight:Dp,
    val listPaddingHorizontal:Dp,

    val actionSheetRoundedCornerDp:Dp,

    val navigationBarIconSize:Dp,
    val navigationBarHeight:Dp,

    val tableIconSize:Dp,

    val switchIconWidth:Dp,
    val switchIconHeight:Dp
)

val DefaultWeDimens = WeDimens(
    topNavigationBarHeight = 56.dp,
    topNavigationBarPaddingHorizontal = 10.dp,
    topNavigationBarActionWidth = 90.dp,
    topNavigationBarIconSize = 24.dp,
    topNavigationBarActionPaddingWidth = 16.dp,

    bigButtonWidth = 320.dp,
    bigButtonHeight = 40.dp,
    bigButtonRoundedCornerDp = 4.dp,
    smallButtonWidth = 120.dp,
    smallButtonHeight = 40.dp,
    smallButtonRoundedCornerDp = 4.dp,

    listSingleRowHeight = 56.dp,
    listDoubleRowHeight = 80.dp,
    listPaddingHorizontal = 16.dp,

    actionSheetRoundedCornerDp = 12.dp,

    navigationBarIconSize = 28.dp,
    navigationBarHeight = 56.dp,

    tableIconSize = 24.dp,

    switchIconWidth = 50.dp,
    switchIconHeight = 30.dp
)

internal val LocalWeDimens = staticCompositionLocalOf { DefaultWeDimens }