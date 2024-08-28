package com.laomuji666.compose.core.ui.we2.widget

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.we2.DefaultWeTheme
import com.laomuji666.compose.core.ui.we2.WeTheme

@Composable
fun WeListRow(
    start: @Composable RowScope.() -> Unit = {},
    center: @Composable RowScope.() -> Unit = { Spacer(modifier = Modifier.weight(1f)) },
    end: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit = {},
    weListRowType: WeListRowType = WeListRowType.SINGLE,
    weListOutlineType: WeListOutlineType = WeListOutlineType.PADDING_HORIZONTAL,
){
    val rowHeight = when(weListRowType){
        WeListRowType.SINGLE -> WeTheme.dimens.listSingleRowHeight
        WeListRowType.DOUBLE -> WeTheme.dimens.listDoubleRowHeight
    }
    Column(
        modifier = Modifier
            .background(WeTheme.colorScheme.background)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = WeTheme.dimens.listPaddingHorizontal)
                .height(rowHeight),
            verticalAlignment = Alignment.CenterVertically
        ){
            start()
            center()
            end()
        }
        WeListOutline(
            weListOutlineType = weListOutlineType
        )
    }

}

enum class WeListRowType{
    SINGLE,
    DOUBLE
}

@PreviewLightDark
@Composable
fun PreviewWeListRow(){
    DefaultWeTheme {
        Column {
            WeListRow()
            WeListRow()
            WeListRow(
                weListOutlineType = WeListOutlineType.SPLIT
            )
            WeListRow(
                weListOutlineType = WeListOutlineType.NONE
            )
        }
    }
}
