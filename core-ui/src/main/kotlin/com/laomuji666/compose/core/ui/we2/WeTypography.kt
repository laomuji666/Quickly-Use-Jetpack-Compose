package com.laomuji666.compose.core.ui.we2

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class WeTypography(
    val heading:TextStyle,
    val emTitle:TextStyle,
    val title:TextStyle,
    val emGroupTitle:TextStyle,
    val groupTitle:TextStyle,
    val groupBody:TextStyle,
    val text:TextStyle,
    val emDesc:TextStyle,
    val desc:TextStyle,
    val footnote:TextStyle,
)

val DefaultWeTypography = WeTypography(
    heading = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        color = WeColors.FontColorLight90
    ),
    emTitle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        color = WeColors.FontColorLight90
    ),
    title = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = WeColors.FontColorLight90
    ),
    emGroupTitle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        color = WeColors.FontColorLight90
    ),
    groupTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = WeColors.FontColorLight50
    ),
    groupBody = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = WeColors.FontColorLight50
    ),
    text = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = WeColors.FontColorLight90
    ),
    emDesc = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = WeColors.FontColorLight50
    ),
    desc = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = WeColors.FontColorLight30
    ),
    footnote = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = WeColors.FontColorLight30
    )
)

internal val LocalWeTypography = staticCompositionLocalOf { DefaultWeTypography }