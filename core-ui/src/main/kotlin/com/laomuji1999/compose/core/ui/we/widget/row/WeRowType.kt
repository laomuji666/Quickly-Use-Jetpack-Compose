package com.laomuji1999.compose.core.ui.we.widget.row

sealed interface WeRowType{
    data object Single : WeRowType
    data object Double : WeRowType
}