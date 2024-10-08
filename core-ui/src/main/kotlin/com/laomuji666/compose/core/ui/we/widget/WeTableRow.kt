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
    modifier: Modifier = Modifier,
    start: @Composable RowScope.() -> Unit = {},
    center: @Composable RowScope.() -> Unit = { Spacer(modifier = Modifier.weight(1f)) },
    end: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = WeTheme.colorScheme.tableRowBackground,
    onClick: () -> Unit = {},
    showClickIndication: Boolean = false,
    weTableRowType: WeTableRowType = WeTableRowType.SINGLE,
    outlineModifier: Modifier = Modifier,
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
){
    val rowHeight = when(weTableRowType){
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
    val pressColor = WeTheme.colorScheme.pressed
    Column(modifier = modifier
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
        .background(backgroundColor)
        .fillMaxWidth()
        .height(rowHeight)
    ){
        Row(
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .fillMaxWidth()
                .weight(1f)
                .weight(1f)
                .drawWithContent {
                    drawContent()
                    if (showPress) {
                        drawRect(color = pressColor, size = size)
                    }
                }
                .padding(horizontal = WeTheme.dimens.listPaddingHorizontal),
            verticalAlignment = Alignment.CenterVertically
        ){
            start()
            center()
            end()
        }
        WeTableRowOutline(
            modifier = outlineModifier,
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
