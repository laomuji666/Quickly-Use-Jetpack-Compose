package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableRowOutline(
    modifier: Modifier = Modifier,
    color: Color = WeTheme.colorScheme.outline,
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.FULL,
){
    when(weTableRowOutlineType){
        WeTableRowOutlineType.NONE -> {

        }
        WeTableRowOutlineType.FULL -> {
            Spacer(
                modifier = modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color)
            )
        }
        WeTableRowOutlineType.SPLIT -> {
            Spacer(
                modifier = modifier
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(color)
            )
        }
        WeTableRowOutlineType.PADDING_HORIZONTAL -> {
            Spacer(
                modifier = modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(horizontal = WeTheme.dimens.listPaddingHorizontal)
                    .background(color)
            )
        }
        WeTableRowOutlineType.PADDING_START -> {
            Spacer(
                modifier = modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(start = WeTheme.dimens.listPaddingHorizontal)
                    .background(color)
            )
        }
    }
}

enum class WeTableRowOutlineType{
    NONE,
    FULL,
    SPLIT,
    PADDING_HORIZONTAL,
    PADDING_START
}