package com.laomuji666.compose.feature.demo.ui

sealed interface UiDemoScreenAction {
    data class SwapDragList(val a:Int,val b:Int): UiDemoScreenAction
}