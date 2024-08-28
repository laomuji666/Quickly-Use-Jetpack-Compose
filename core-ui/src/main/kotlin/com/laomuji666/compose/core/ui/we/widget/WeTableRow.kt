package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableRow(
    start: @Composable RowScope.() -> Unit = {},
    center: @Composable RowScope.() -> Unit = { Spacer(modifier = Modifier.weight(1f)) },
    end: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit = {},
    rowHeight: Dp = WeTheme.weDimens.rowHeightDp,
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.NONE
){
    Column {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .background(WeTheme.weColorScheme.rowBackgroundColor)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = WeTheme.weDimens.paddingHorizontalDp)
                    .height(rowHeight),
                verticalAlignment = Alignment.CenterVertically
            ){
                start()
                center()
                end()
            }
            if(weTableRowOutlineType != WeTableRowOutlineType.SPLIT){
                Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                    WeTableRowOutline(weTableRowOutlineType)
                }
            }
        }
        if(weTableRowOutlineType == WeTableRowOutlineType.SPLIT){
            WeTableRowOutline(weTableRowOutlineType)
        }
    }
}