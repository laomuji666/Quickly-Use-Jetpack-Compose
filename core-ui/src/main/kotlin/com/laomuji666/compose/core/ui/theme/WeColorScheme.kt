package com.laomuji666.compose.core.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * @param fontBaseColor 文字基准色
 * @param bigTitleColor 大页面标题
 * @param middleTitleColor 弹窗标题
 * @param smallTitleColor 列表内标题
 * @param groupTitleColor 分组标题
 * @param bigTextColor 大页面文字
 * @param middleTextColor 弹窗文字
 * @param smallTextColor 描述文字
 * @param topBarColor 顶部导航背景色
 * @param snackBarColor 快捷信息栏背景色
 * @param rowItemColor 一行内容背景色
 * @param backgroundColor 页面背景色
 */
data class WeColorScheme (
    val fontBaseColor: Color,
    val bigTitleColor: Color = fontBaseColor.copy(alpha = 0.9f),
    val middleTitleColor: Color = fontBaseColor.copy(alpha = 0.9f),
    val smallTitleColor: Color = fontBaseColor.copy(alpha = 0.9f),
    val groupTitleColor: Color = fontBaseColor.copy(alpha = 0.5f),
    val bigTextColor: Color = fontBaseColor.copy(alpha = 0.9f),
    val middleTextColor: Color = fontBaseColor.copy(alpha = 0.5f),
    val smallTextColor: Color = fontBaseColor.copy(alpha = 0.5f),
    val topBarColor: Color,
    val snackBarColor: Color,
    val rowItemColor: Color,
    val backgroundColor: Color
)

val lightWeColorScheme = WeColorScheme(
    fontBaseColor = Color(0xFF000000),
    topBarColor = Color(0xFFEDEDED),
    snackBarColor = Color(0xFFEDEDED),
    rowItemColor = Color(0xFFFFFFFF),
    backgroundColor = Color(0xFFEDEDED)
)

val darkWeColorScheme = WeColorScheme(
    fontBaseColor = Color(0xFFFFFFFF),
    topBarColor = Color(0xFF111111),
    snackBarColor = Color(0xFF242424),
    rowItemColor = Color(0xFF191919),
    backgroundColor = Color(0xFF111111)
)