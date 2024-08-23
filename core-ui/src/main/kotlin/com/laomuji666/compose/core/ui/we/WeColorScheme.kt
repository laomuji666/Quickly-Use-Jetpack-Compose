package com.laomuji666.compose.core.ui.we

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * @param fontBaseColor 文字基准色
 * @param topBarColor 顶部导航背景色
 * @param snackBarColor 快捷信息栏背景色
 * @param rowItemColor 一行内容背景色
 * @param backgroundColor 页面背景色
 */
data class WeColorScheme (
    val fontBaseColor: Color,
    val topBarColor: Color,
    val onTopBarColor: Color,
    val snackBarColor: Color,
    val rowItemColor: Color,
    val backgroundColor: Color
)

val lightWeColorScheme = WeColorScheme(
    fontBaseColor = Color(0xFF000000),
    topBarColor = Color(0xFFEDEDED).copy(alpha = 0.9f),
    onTopBarColor = Color(0xFF000000),
    snackBarColor = Color(0xFFEDEDED),
    rowItemColor = Color(0xFFFFFFFF),
    backgroundColor = Color(0xFFEDEDED),
)

val darkWeColorScheme = WeColorScheme(
    fontBaseColor = Color(0xFFFFFFFF),
    topBarColor = Color(0xFF111111),
    onTopBarColor = Color(0xFFFFFFFF).copy(alpha = 0.9f),
    snackBarColor = Color(0xFF242424),
    rowItemColor = Color(0xFF191919),
    backgroundColor = Color(0xFF111111),
)

internal val LocalWeColorScheme = staticCompositionLocalOf { lightWeColorScheme }

@Stable
fun WeColorScheme.contentColorFor(containerColor: Color): Color =
    when (containerColor) {
        topBarColor -> onTopBarColor
        else -> Color.Unspecified
    }


@Composable
@ReadOnlyComposable
fun contentColorFor(containerColor: Color) = WeTheme.weColorScheme.contentColorFor(containerColor)