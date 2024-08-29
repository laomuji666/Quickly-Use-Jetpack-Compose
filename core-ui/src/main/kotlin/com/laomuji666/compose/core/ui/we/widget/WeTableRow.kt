package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        WeTableRowType.SINGLE -> WeTheme.dimens.listSingleRowHeight
        WeTableRowType.DOUBLE -> WeTheme.dimens.listDoubleRowHeight
    }
    var showClick by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .background(WeTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                if (showClickIndication) {
                    showClick = true
                    coroutineScope.launch {
                        delay(100)
                        showClick = false
                    }
                }
                onClick()
            }
            .fillMaxWidth()
            .height(rowHeight)
            .drawWithContent {
                drawContent()
                if (showClick) {
                    drawRect(color = Color.Black.copy(alpha = 0.3f), size = size)
                }
            }
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = WeTheme.dimens.listPaddingHorizontal),
                verticalAlignment = Alignment.CenterVertically
            ){
                start()
                center()
                end()
            }
            if(weTableRowOutlineType != WeTableRowOutlineType.SPLIT){
                WeTableRowOutline(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    weTableRowOutlineType = weTableRowOutlineType
                )
            }
        }
        if(weTableRowOutlineType == WeTableRowOutlineType.SPLIT){
            WeTableRowOutline(
                weTableRowOutlineType = weTableRowOutlineType
            )
        }
    }

}

enum class WeTableRowType{
    SINGLE,
    DOUBLE
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
