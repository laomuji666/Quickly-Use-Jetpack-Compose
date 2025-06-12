package com.laomuji1999.compose.core.ui.we.widget.toast

sealed interface WeToastType {
    data object Done : WeToastType
    data object Loading : WeToastType
    data object Error : WeToastType
}