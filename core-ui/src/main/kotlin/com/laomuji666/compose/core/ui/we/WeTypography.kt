package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * @param largeTitle 大页面标题,推荐透明0.9f
 * @param mediumTitle 弹窗标题,推荐透明0.9f
 * @param smallTitle 列表内标题,推荐透明0.9f
 * @param groupTitle 分组标题,推荐透明0.5f
 * @param largeText 大页面文字,推荐透明0.9f
 * @param mediumText 弹窗文字,推荐透明0.5f
 * @param smallText 描述文字,推荐透明0.3f
 */
data class WeTypography(
    val largeTitle: TextStyle = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    ),
    val mediumTitle: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    val smallTitle: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    val groupTitle: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    val largeText: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium
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