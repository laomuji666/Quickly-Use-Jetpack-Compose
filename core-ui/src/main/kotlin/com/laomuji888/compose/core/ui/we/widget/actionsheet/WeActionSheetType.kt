package com.laomuji888.compose.core.ui.we.widget.actionsheet

sealed interface WeActionSheetType {
    data object Summary : WeActionSheetType
    data object Normal : WeActionSheetType
    data object Wrong : WeActionSheetType
    data object Primary : WeActionSheetType
}