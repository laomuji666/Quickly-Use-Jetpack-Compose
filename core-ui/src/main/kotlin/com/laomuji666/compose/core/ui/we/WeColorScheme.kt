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
    val onToastBackgroundColor: Color,
    val error: Color = Color(0xFFFA5151),
    val primary: Color = Color(0xFF07C160),
    val secondary: Color = Color(0xFFE5E5E5),
    val tertiary: Color = Color(0xFF000000).copy(alpha = 0.18f)
)
val lightWeColorScheme = WeColorScheme(
    backgroundColor = Color(0xFFEDEDED).copy(alpha = 0.9f),
    onBackgroundColor = Color(0xFF000000).copy(alpha = 0.9f),
    rowBackgroundColor = Color(0xFFFFFFFF),
    onRowBackSecondaryColor = Color(0xFF000000).copy(alpha = 0.5f),
    outlineColor = Color(0xFFEDEDED).copy(alpha = 0.8f),
    splitLineColor = Color(0xFFF2F2F2),
    indicationColor = Color(0xFFEDEDED).copy(alpha = 0.9f),
    indicationBlendMode = BlendMode.DstIn,
    onToastBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f)
)

val darkWeColorScheme = WeColorScheme(
    backgroundColor = Color(0xFF111111),
    onBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f),
    rowBackgroundColor = Color(0xFF191919),
    onRowBackSecondaryColor = Color(0xFFFFFFFF).copy(alpha = 0.5f),
    outlineColor = Color(0xFFEDEDED).copy(alpha = 0.035f),
    splitLineColor = Color(0xFF111111),
    indicationColor = Color(0xFFEDEDED).copy(alpha = 0.06f),
    indicationBlendMode = BlendMode.SrcOver,
    onToastBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f)
)

internal val LocalWeColorScheme = staticCompositionLocalOf { lightWeColorScheme }