package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableRow(
    start: @Composable RowScope.() -> Unit = {},
    center: @Composable RowScope.() -> Unit = { Spacer(modifier = Modifier.weight(1f)) },
    end: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit = {},
    showClickIndication: Boolean = true,
    weTableRowRowType: WeTableRowType = WeTableRowType.SINGLE,
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
){
    val rowHeight = when(weTableRowRowType){
        WeTableRowType.SINGLE -> if(weTableRowOutlineType == WeTableRowOutlineType.SPLIT){
            WeTheme.dimens.listSingleRowHeight + WeTheme.dimens.outlineSplitHeight
        }else{
            WeTheme.dimens.listSingleRowHeight
        }
        WeTableRowType.DOUBLE -> if(weTableRowOutlineType == WeTableRowOutlineType.SPLIT){
            WeTheme.dimens.listDoubleRowHeight + WeTheme.dimens.outlineSplitHeight
        }else{
            WeTheme.dimens.listDoubleRowHeight
        }
    }
    var showPress by remember  { mutableStateOf(false) }
    Column(modifier = Modifier.background(WeTheme.colorScheme.tableRowBackground)
        .pointerInput(Unit){
            if(showClickIndication){
                detectPress(
                    onPress = {
                        showPress = true
                    },
                    onRelease = {
                        showPress = false
                    }
                )
            }
        }
        .fillMaxWidth()
        .height(rowHeight)
        .drawWithContent {
            drawContent()
            if (showPress) {
                drawRect(color = Color.Black.copy(alpha = 0.1f), size = size)
            }
        }
    ){
        Row(
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .weight(1f)
                .padding(horizontal = WeTheme.dimens.listPaddingHorizontal),
            verticalAlignment = Alignment.CenterVertically
        ){
            start()
            center()
            end()
        }
        WeTableRowOutline(
            weTableRowOutlineType = weTableRowOutlineType
        )
    }

}

enum class WeTableRowType{
    SINGLE,
    DOUBLE
}

private suspend fun PointerInputScope.detectPress(
    onPress: () -> Unit,
    onRelease: () -> Unit,
) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            if (event.changes.any { it.pressed }) {
                onPress()
            } else {
                onRelease()
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTableRow(){
    DefaultWeTheme {
        Column {
            WeTableRow()
            WeTableRow()
            WeTableRow(
                weTableRowOutlineType = WeTableRowOutlineType.SPLIT
            )
            WeTableRow(
                weTableRowOutlineType = WeTableRowOutlineType.NONE
            )
        }
    }
}
