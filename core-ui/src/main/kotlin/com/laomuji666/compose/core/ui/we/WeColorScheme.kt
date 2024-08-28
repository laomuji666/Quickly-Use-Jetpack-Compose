package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintBlendMode

data class WeColorScheme (
    val backgroundColor: Color,
    val onBackgroundColor: Color,
    val rowBackgroundColor: Color,
    val onRowBackSecondaryColor: Color,
    val outlineColor: Color,
    val splitLineColor: Color,
    val indicationColor:Color = Color.Black.copy(alpha = 0.3f),
    val indicationBlendMode: BlendMode = DefaultTintBlendMode,
    val error: Color = Color(0xFFFA5151),
    val popMenuBackgroundColor: Color,
    val onPopMenuBackgroundColor: Color = Color(0xFFFFFFFF).copy(alpha = 0.9f),
)
val lightWeColorScheme = WeColorScheme(
    backgroundColor = Color(0xFFEDEDED).copy(alpha = 0.9f),
    onBackgroundColor = Color(0xFF000000).copy(alpha = 0.9f),
    rowBackgroundColor = Color(0xFFFFFFFF),
    onRowBackSecondaryColor = Color(0xFF000000).copy(alpha = 0.5f),
    splitLineColor = Color(0xFFF2F2F2),
    indicationColor = Color(0xFFEDEDED).copy(alpha = 0.9f),
    indicationBlendMode = BlendMode.DstIn,
    popMenuBackgroundColor = Color(0xFF4C4C4C),
    outlineColor = Color(0xFFEDEDED).copy(alpha = 0.3f)
)

val darkWeColorScheme = WeColorScheme(
    backgroundColor = Color(0xFF111111),
    onBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f),
    rowBackgroundColor = Color(0xFF191919),
    onRowBackSecondaryColor = Color(0xFFFFFFFF).copy(alpha = 0.5f),
    splitLineColor = Color(0xFF111111),
    indicationColor = Color(0xFFEDEDED).copy(alpha = 0.06f),
    indicationBlendMode = BlendMode.SrcOver,
    popMenuBackgroundColor = Color(0xFF404040),
    outlineColor = Color(0xFFEDEDED).copy(alpha = 0.05f)
)

internal val LocalWeColorScheme = staticCompositionLocalOf { lightWeColorScheme }