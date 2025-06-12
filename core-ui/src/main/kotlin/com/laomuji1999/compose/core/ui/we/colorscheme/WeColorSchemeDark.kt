package com.laomuji1999.compose.core.ui.we.colorscheme

/**
 * 夜间风格颜色
 * @author laomuji666
 * @since 2025/5/23
 */
data object WeColorSchemeDark : WeColorScheme(
    isDarkFont = false,

    background = WeColors.Color_000000,
    cursorColor = WeColors.Color_07C160,

    fontColorHeavy = WeColors.Color_FFFFFF_90,
    fontColorLight = WeColors.Color_FFFFFF_50,
    fontColorError = WeColors.Color_FA5151,
    fontColorPrimary = WeColors.Color_07C160,

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

    bottomBarBackground = WeColors.Color_191919,
    bottomBarSelect = WeColors.Color_07C160,
    bottomBarUnSelect = WeColors.Color_FFFFFF_90,
    toastBackgroundColor = WeColors.Color_4C4C4C_90,
    onToastBackgroundColor = WeColors.Color_FFFFFF_90,

    switchThumbColor = WeColors.Color_FFFFFFFF,
    switchSelectBackground = WeColors.Color_07C160,
    switchUnSelectBackground = WeColors.Color_FFFFFF_08,

    rowBackground = WeColors.Color_191919,

    chatInputBackground = WeColors.Color_191919,
    chatMessageBackgroundSend = WeColors.Color_3EB477,
    chatMessageBackgroundReceive = WeColors.Color_191919,
    chatMessageTextSend = WeColors.Color_000000_90,
    chatMessageTextReceive = WeColors.Color_FFFFFF_90,

    categoryTextColor = WeColors.Color_FFFFFF_90,
    categoryBackground = WeColors.Color_07C160,
)