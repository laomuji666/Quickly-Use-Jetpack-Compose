package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintBlendMode

/**
 * @param backgroundColor 页面背景色
 * @param onBackgroundColor 背景上的文字颜色
 * @param rowBackgroundColor 行内容背景色
 * @param onRowBackgroundColor 行内容上的文字颜色
 * @param onRowBackSecondaryColor 行内容上的辅助文字颜色
 */
data class WeColorScheme (
    val backgroundColor: Color,
    val onBackgroundColor: Color,
    val rowBackgroundColor: Color,
    val onRowBackgroundColor: Color,
    val onRowBackSecondaryColor: Color,
    val outlineColor: Color,
    val indicationColor:Color = Color.Black.copy(alpha = 0.3f),
    val indicationBlendMode: BlendMode = DefaultTintBlendMode,
    val toastBackgroundColor: Color,
    val onToastBackgroundColor: Color,
)

val lightWeColorScheme = WeColorScheme(
    backgroundColor = Color(0xFFEDEDED).copy(alpha = 0.9f),
    onBackgroundColor = Color(0xFF000000),
    rowBackgroundColor = Color(0xFFFFFFFF),
    onRowBackgroundColor = Color(0xFF000000).copy(alpha = 0.9f),
    onRowBackSecondaryColor = Color(0xFF000000).copy(alpha = 0.5f),
    outlineColor = Color(0xFFEDEDED).copy(alpha = 0.8f),
    indicationColor = Color(0xFFEDEDED).copy(alpha = 0.9f),
    indicationBlendMode = BlendMode.DstIn,
    toastBackgroundColor = Color(0xFF4C4C4C).copy(alpha = 0.9f),
    onToastBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f)
)

val darkWeColorScheme = WeColorScheme(
    backgroundColor = Color(0xFF111111),
    onBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f),
    rowBackgroundColor = Color(0xFF191919),
    onRowBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f),
    onRowBackSecondaryColor = Color(0xFFFFFFFF).copy(alpha = 0.5f),
    outlineColor = Color(0xFFEDEDED).copy(alpha = 0.035f),
    indicationColor = Color(0xFFEDEDED).copy(alpha = 0.06f),
    indicationBlendMode = BlendMode.SrcOver,
    toastBackgroundColor = Color(0xFF606060).copy(alpha = 0.9f),
    onToastBackgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.9f)
)

internal val LocalWeColorScheme = staticCompositionLocalOf { lightWeColorScheme }