package com.laomuji1999.compose.core.ui.we.widget.button

sealed interface WeButtonType {
    data object Big : WeButtonType
    data object Small : WeButtonType
    data object Warp : WeButtonType
}