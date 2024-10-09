package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WeDimens(
    val navigationBarHeight:Dp,
    val navigationBarPaddingHorizontal:Dp,
    val navigationBarActionWidth:Dp,
    val navigationBarIconSize:Dp,
    val navigationBarActionPaddingWidth:Dp,

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

    val bottomNavigationBarIconSize:Dp,
    val bottomNavigationBarHeight:Dp,

    val tableIconSize:Dp,

    val switchIconWidth:Dp,
    val switchIconHeight:Dp,

    val toastSize:Dp,
    val toastIconSize:Dp,
    val toastDividerSize:Dp,

    val outlineHeight:Dp,
    val outlineSplitHeight:Dp,

    val contactIconSize:Dp,
    val contactIconRoundedCornerDp: Dp,

    val tableLabelWidth:Dp,

    val chatInputHeight:Dp,
)

val DefaultWeDimens = WeDimens(
    navigationBarHeight = 44.dp,
    navigationBarPaddingHorizontal = 10.dp,
    navigationBarActionWidth = 90.dp,
    navigationBarIconSize = 24.dp,
    navigationBarActionPaddingWidth = 16.dp,

    bigButtonWidth = 320.dp,
    bigButtonHeight = 40.dp,
    bigButtonRoundedCornerDp = 4.dp,
    smallButtonWidth = 120.dp,
    smallButtonHeight = 40.dp,
    smallButtonRoundedCornerDp = 4.dp,

    listSingleRowHeight = 54.dp,
    listDoubleRowHeight = 80.dp,
    listPaddingHorizontal = 16.dp,

    actionSheetRoundedCornerDp = 12.dp,

    bottomNavigationBarIconSize = 26.dp,
    bottomNavigationBarHeight = 52.dp,

    tableIconSize = 24.dp,

    switchIconWidth = 50.dp,
    switchIconHeight = 30.dp,

    toastSize = 136.dp,
    toastIconSize = 40.dp,
    toastDividerSize = 16.dp,

    outlineHeight = 1.dp,
    outlineSplitHeight = 8.dp,

    contactIconSize = 40.dp,
    contactIconRoundedCornerDp = 4.dp,

    tableLabelWidth = 60.dp,

    chatInputHeight = 56.dp
)

internal val LocalWeDimens = staticCompositionLocalOf { DefaultWeDimens }