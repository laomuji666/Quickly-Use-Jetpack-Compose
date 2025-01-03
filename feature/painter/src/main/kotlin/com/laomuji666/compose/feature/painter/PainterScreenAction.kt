package com.laomuji666.compose.feature.painter

import androidx.compose.ui.geometry.Offset

sealed interface PainterScreenAction{
    data object OnClearCanvas:PainterScreenAction
    data class OnCurrentColorIndexChange(val index:Int):PainterScreenAction
    data class OnCurrentWidthIndexChange(val index:Int):PainterScreenAction
    data class OnDragStart(val offset: Offset):PainterScreenAction
    data object OnDragEnd:PainterScreenAction
    data class OnDrag(val offset: Offset):PainterScreenAction
}