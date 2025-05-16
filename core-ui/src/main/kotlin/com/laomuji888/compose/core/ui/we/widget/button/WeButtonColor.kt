package com.laomuji888.compose.core.ui.we.widget.button

sealed interface WeButtonColor {
    data object Primary : WeButtonColor
    data object Secondary : WeButtonColor
    data object Disable : WeButtonColor
    data object Wrong : WeButtonColor
}