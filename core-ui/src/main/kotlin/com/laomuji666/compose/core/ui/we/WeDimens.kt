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
    val toastIconSize:Dp,
    val roundedCornerDp:Dp,
    val bigButtonWidthDp:Dp,
    val bigButtonHeightDp:Dp,
    val smallButtonWidthDp:Dp,
    val smallButtonHeightDp:Dp,
    val buttonRoundedCornerDp:Dp
)

val DefaultWeDimens = WeDimens(
    topBarHeightDp = 44.dp,
    topBarPaddingBottomDp = 5.dp,
    topBarActionRowWidthDp = 90.dp,
    paddingHorizontalDp = 16.dp,
    rowHeightDp = 56.dp,
    twoRowHeightDp = 80.dp,
    iconHeightDp = 28.dp,
    toastSize = 136.dp,
    toastIconSize = 40.dp,
    roundedCornerDp = 12.dp,
    bigButtonWidthDp = 184.dp,
    bigButtonHeightDp = 40.dp,
    smallButtonWidthDp = 58.dp,
    smallButtonHeightDp = 32.dp,
    buttonRoundedCornerDp = 4.dp
)


internal val LocalWeDimens = staticCompositionLocalOf { DefaultWeDimens }