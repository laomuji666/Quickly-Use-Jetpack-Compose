package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * @param largeTitle 大页面标题
 * @param mediumTitle 弹窗标题
 * @param smallTitle 列表内标题
 * @param groupTitle 分组标题
 * @param largeText 大页面文字
 * @param mediumText 弹窗文字
 * @param smallText 描述文字
 */
data class WeTypography(
    val largeTitle: TextStyle = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    ),
    val mediumTitle: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium
    ),
    val smallTitle: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    val groupTitle: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium
    ),
    val largeText: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    val mediumText: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    val smallText: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
)

val defaultTypography = WeTypography()

internal val LocalWeTypography = staticCompositionLocalOf { defaultTypography }