package com.laomuji666.compose.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * @param weColorScheme 颜色
 * @param bigTitle 大页面标题
 * @param middleTitle 弹窗标题
 * @param smallTitle 列表内标题
 * @param groupTitle 分组标题
 * @param bigText 大页面文字
 * @param middleText 弹窗文字
 * @param smallText 描述文字
 */
data class WeTypography(
    val weColorScheme: WeColorScheme,
    val bigTitle: TextStyle = TextStyle(
        color = weColorScheme.bigTitleColor,
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    ),
    val middleTitle: TextStyle = TextStyle(
        color = weColorScheme.middleTitleColor,
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium
    ),
    val smallTitle: TextStyle = TextStyle(
        color = weColorScheme.smallTitleColor,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    val groupTitle: TextStyle = TextStyle(
        color = weColorScheme.groupTitleColor,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium
    ),
    val bigText: TextStyle = TextStyle(
        color = weColorScheme.bigTextColor,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    val middleText: TextStyle = TextStyle(
        color = weColorScheme.middleTextColor,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    val smallText: TextStyle = TextStyle(
        color = weColorScheme.smallTextColor,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
)

val lightWeTypography = WeTypography(lightWeColorScheme)

val darkWeTypography = WeTypography(darkWeColorScheme)