package com.laomuji1999.compose.core.ui.we.widget.outline

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface WeOutlineType {
    data object None : WeOutlineType
    data object Full : WeOutlineType
    data object PaddingHorizontal : WeOutlineType
    data object PaddingStart : WeOutlineType
    data object Split : WeOutlineType
    data class Custom(val height: Dp = 0.dp, val start: Dp = 0.dp, val end: Dp = 0.dp) : WeOutlineType
}