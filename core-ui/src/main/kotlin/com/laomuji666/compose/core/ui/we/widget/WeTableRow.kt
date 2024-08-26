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
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.LocalWeDimens
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableRow(
    start: @Composable RowScope.() -> Unit,
    center: @Composable RowScope.() -> Unit = { Spacer(modifier = Modifier.weight(1f)) },
    end: @Composable RowScope.() -> Unit,
    onClick: () -> Unit = {},
    rowHeight: Dp = LocalWeDimens.current.rowHeightDp,
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
                    .padding(horizontal = LocalWeDimens.current.paddingHorizontalDp)
                    .height(rowHeight),
                verticalAlignment = Alignment.CenterVertically
            ){
                start()
                center()
                end()
            }
            if(weTableRowOutlineType == WeTableRowOutlineType.FULL){
                Spacer(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(WeTheme.weColorScheme.outlineColor)
                )
            }
            if(weTableRowOutlineType == WeTableRowOutlineType.PADDING_START){
                Spacer(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(start = LocalWeDimens.current.paddingHorizontalDp)
                        .height(1.dp)
                        .background(WeTheme.weColorScheme.outlineColor)
                )
            }
            if(weTableRowOutlineType == WeTableRowOutlineType.PADDING_HORIZONTAL){
                Spacer(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = LocalWeDimens.current.paddingHorizontalDp)
                        .height(1.dp)
                        .background(WeTheme.weColorScheme.outlineColor)
                )
            }
        }
        if(weTableRowOutlineType == WeTableRowOutlineType.SPLIT){
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(WeTheme.weColorScheme.splitLineColor)
            )
        }
    }
}

enum class WeTableRowOutlineType{
    NONE,
    FULL,
    PADDING_START,
    PADDING_HORIZONTAL,
    SPLIT
}