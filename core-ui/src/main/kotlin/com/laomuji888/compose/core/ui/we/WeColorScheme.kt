package com.laomuji888.compose.core.ui.we

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class WeColorScheme(
    //背景色
    val background: Color,
    //光标色
    val cursorColor: Color,

    //重字体色
    val fontColorDark: Color,
    //轻字体色
    val fontColorLight: Color,
    //异常字体色
    val fontError: Color,

    //主操作按钮
    val primaryButton: Color,
    val onPrimaryButton: Color,
    //弱化按钮
    val secondaryButton: Color,
    val onSecondaryButton: Color,
    //失效按钮
    val disableButton: Color,
    val onDisableButton: Color,
    //警告按钮
    val wrongButton: Color,
    val onWrongButton: Color,

    //分割线色
    val outline: Color,
    //可点击的触摸色
    val pressed: Color,

    //列表组件背景色
    val tableRowBackground: Color,
    val tableRowInputBackground: Color,

    //开关组件颜色
    val switchThumbColor: Color,
    val switchSelectBackground: Color,
    val switchUnSelectBackground: Color,

    //底部导航组件颜色
    val bottomNavigationBarSelect: Color,
    val bottomNavigationBarUnSelect: Color,
    val bottomNavigationBarBackground: Color,

    //Toast组件颜色
    val toastBackgroundColor: Color,
    val onToastBackgroundColor: Color,


    //聊天页面,特定颜色
    val chatInputBackground: Color,
    val chatMessageBackgroundSend: Color,
    val chatMessageBackgroundReceive: Color,
    val chatMessageTextSend: Color,
    val chatMessageTextReceive: Color,
)

val LightWeColorScheme = WeColorScheme(
    background = WeColors.Color_EDEDED,
    cursorColor = WeColors.Color_07C160,


    fontColorDark = WeColors.Color_000000_90,
    fontColorLight = WeColors.Color_000000_50,


    primaryButton = WeColors.Color_07C160,
    onPrimaryButton = WeColors.Color_FFFFFFFF,
    secondaryButton = WeColors.Color_F7F7F7,
    onSecondaryButton = WeColors.Color_07C160,
    disableButton = WeColors.Color_F7F7F7,
    onDisableButton = WeColors.Color_000000_10,
    wrongButton = WeColors.Color_F7F7F7,
    onWrongButton = WeColors.Color_FA5151,


    outline = WeColors.Color_000000_10,
    pressed = WeColors.Color_000000_08,
    fontError = WeColors.Color_FA5151,

    bottomNavigationBarBackground = WeColors.Color_EDEDED,
    bottomNavigationBarSelect = WeColors.Color_07C160,
    bottomNavigationBarUnSelect = WeColors.Color_000000_90,
    toastBackgroundColor = WeColors.Color_4C4C4C_90,
    onToastBackgroundColor = WeColors.Color_FFFFFF_90,

    switchThumbColor = WeColors.Color_FFFFFFFF,
    switchSelectBackground = WeColors.Color_07C160,
    switchUnSelectBackground = WeColors.Color_000000_10,


    tableRowBackground = WeColors.Color_FFFFFFFF,
    tableRowInputBackground = WeColors.Color_TRANSPARENT,


    chatInputBackground = WeColors.Color_EDEDED,
    chatMessageBackgroundSend = WeColors.Color_95EC6A,
    chatMessageBackgroundReceive = WeColors.Color_FFFFFFFF,
    chatMessageTextSend = WeColors.Color_000000_90,
    chatMessageTextReceive = WeColors.Color_000000_90,
)

val DarkWeColorScheme = WeColorScheme(
    background = WeColors.Color_000000,
    cursorColor = WeColors.Color_07C160,


    fontColorDark = WeColors.Color_FFFFFF_90,
    fontColorLight = WeColors.Color_FFFFFF_50,


    primaryButton = WeColors.Color_07C160,
    onPrimaryButton = WeColors.Color_FFFFFFFF,
    secondaryButton = WeColors.Color_FFFFFF_15,
    onSecondaryButton = WeColors.Color_07C160,
    disableButton = WeColors.Color_FFFFFF_15,
    onDisableButton = WeColors.Color_FFFFFF_15,
    wrongButton = WeColors.Color_FFFFFF_15,
    onWrongButton = WeColors.Color_FA5151,


    outline = WeColors.Color_FFFFFF_08,
    pressed = WeColors.Color_FFFFFF_05,
    fontError = WeColors.Color_FA5151,

    bottomNavigationBarBackground = WeColors.Color_191919,
    bottomNavigationBarSelect = WeColors.Color_07C160,
    bottomNavigationBarUnSelect = WeColors.Color_FFFFFF_90,
    toastBackgroundColor = WeColors.Color_4C4C4C_90,
    onToastBackgroundColor = WeColors.Color_FFFFFF_90,

    switchThumbColor = WeColors.Color_FFFFFFFF,
    switchSelectBackground = WeColors.Color_07C160,
    switchUnSelectBackground = WeColors.Color_FFFFFF_08,


    tableRowBackground = WeColors.Color_191919,
    tableRowInputBackground = WeColors.Color_TRANSPARENT,


    chatInputBackground = WeColors.Color_191919,
    chatMessageBackgroundSend = WeColors.Color_3EB477,
    chatMessageBackgroundReceive = WeColors.Color_191919,
    chatMessageTextSend = WeColors.Color_000000_90,
    chatMessageTextReceive = WeColors.Color_FFFFFF_90,
)

internal val LocalWeColorScheme = staticCompositionLocalOf { LightWeColorScheme }