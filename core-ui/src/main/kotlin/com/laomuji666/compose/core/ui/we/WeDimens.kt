package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WeDimens(
    val topBarHeightDp:Dp,
    val topBarPaddingBottomDp:Dp,
    val topBarActionRowWidthDp:Dp,
    val paddingHorizontalDp:Dp,
    val rowHeightDp:Dp,
    val twoRowHeightDp:Dp,
    val iconHeightDp:Dp,
    val toastSize:Dp,
    val toastIconSize:Dp
)

val DefaultWeDimens = WeDimens(
    topBarHeightDp = 44.dp,
    topBarPaddingBottomDp = 5.dp,
    topBarActionRowWidthDp = 90.dp,
    paddingHorizontalDp = 16.dp,
    rowHeightDp = 56.dp,
    twoRowHeightDp = 80.dp,
    iconHeightDp = 24.dp,
    toastSize = 136.dp,
    toastIconSize = 40.dp
)


internal val LocalWeDimens = staticCompositionLocalOf { DefaultWeDimens }