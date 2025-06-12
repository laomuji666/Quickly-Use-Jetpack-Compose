package com.laomuji1999.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class WeTypography(
    val emTitle:TextStyle,
    val title:TextStyle,
    val desc:TextStyle,
    val footnote:TextStyle,
    val small:TextStyle
)

val WeTypographyDefault = WeTypography(
    emTitle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    title = TextStyle(
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
    ),
    small = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)

internal val LocalWeTypography = staticCompositionLocalOf { WeTypographyDefault }