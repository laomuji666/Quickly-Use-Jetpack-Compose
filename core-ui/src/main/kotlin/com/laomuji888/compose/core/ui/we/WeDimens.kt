package com.laomuji888.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WeDimens(
    //事件(有点击效果的) Icon 尺寸
    val actionIconSize: Dp,

    //顶部导航 尺寸
    val topActionBar: Dp,
    val topActionBarPaddingHorizontal: Dp,
    val topActionBarBarActionWidth: Dp,
    val topActionBarActionPaddingWidth: Dp,

    //底部导航 尺寸
    val bottomNavigationBarIconSize: Dp,
    val bottomNavigationBarHeight: Dp,

    //按钮 尺寸
    val bigButtonWidth: Dp,
    val bigButtonHeight: Dp,
    val bigButtonRoundedCornerDp: Dp,
    val smallButtonWidth: Dp,
    val smallButtonHeight: Dp,
    val smallButtonRoundedCornerDp: Dp,
    val warpButtonHorizontalPaddingDp: Dp,

    //列表组件 尺寸
    val tableRowSingleRowHeight: Dp,
    val tableRowDoubleRowHeight: Dp,
    val tableRowPaddingHorizontal: Dp,
    val tableRowInnerPaddingHorizontal: Dp,
    val tableRowIconSize: Dp,
    val tableRowIconRoundedCornerDp: Dp,
    val tableLabelWidth: Dp,

    //Action Sheet 尺寸
    val actionSheetRoundedCornerDp: Dp,

    //开关按钮宽度
    val switchIconWidth: Dp,
    //开关按钮高度,不能超过宽度
    val switchIconHeight: Dp,

    //Toast 尺寸
    val toastSize: Dp,
    val toastIconSize: Dp,
    val toastDividerHeight: Dp,

    //Outline 尺寸
    val outlineHeight: Dp,
    val outlineSplitHeight: Dp,

    //聊天页面,特定尺寸
    val chatInputHeight: Dp,
    val chatPaddingHorizontal: Dp,
    val chatAvatarSize: Dp,
    val chatAvatarRoundedCornerDp: Dp
)

val DefaultWeDimens = WeDimens(
    actionIconSize = 24.dp,

    topActionBar = 44.dp,
    topActionBarPaddingHorizontal = 10.dp,
    topActionBarBarActionWidth = 90.dp,
    topActionBarActionPaddingWidth = 16.dp,

    bigButtonWidth = 320.dp,
    bigButtonHeight = 40.dp,
    bigButtonRoundedCornerDp = 4.dp,
    smallButtonWidth = 120.dp,
    smallButtonHeight = 40.dp,
    smallButtonRoundedCornerDp = 4.dp,
    warpButtonHorizontalPaddingDp = 10.dp,

    tableRowSingleRowHeight = 54.dp,
    tableRowDoubleRowHeight = 80.dp,
    tableRowPaddingHorizontal = 16.dp,
    tableRowInnerPaddingHorizontal = 8.dp,

    actionSheetRoundedCornerDp = 12.dp,

    bottomNavigationBarIconSize = 26.dp,
    bottomNavigationBarHeight = 52.dp,


    switchIconWidth = 50.dp,
    switchIconHeight = 30.dp,

    toastSize = 136.dp,
    toastIconSize = 40.dp,
    toastDividerHeight = 16.dp,

    outlineHeight = (0.5).dp,
    outlineSplitHeight = 8.dp,

    tableRowIconSize = 40.dp,
    tableRowIconRoundedCornerDp = 4.dp,

    tableLabelWidth = 60.dp,

    chatInputHeight = 56.dp,
    chatPaddingHorizontal = 10.dp,
    chatAvatarSize = 40.dp,
    chatAvatarRoundedCornerDp = 4.dp
)

internal val LocalWeDimens = staticCompositionLocalOf { DefaultWeDimens }