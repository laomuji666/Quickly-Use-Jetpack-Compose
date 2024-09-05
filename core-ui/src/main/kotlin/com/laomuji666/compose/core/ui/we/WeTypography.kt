package com.laomuji666.compose.core.ui.we

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
    val desc:TextStyle,
    val footnote:TextStyle,
)

val DefaultWeTypography = WeTypography(
    heading = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    emTitle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    title = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    emGroupTitle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    groupTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    groupBody = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    text = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    desc = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    footnote = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)

internal val LocalWeTypography = staticCompositionLocalOf { DefaultWeTypography }