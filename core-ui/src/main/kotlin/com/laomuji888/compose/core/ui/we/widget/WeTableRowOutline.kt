package com.laomuji888.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.laomuji888.compose.core.ui.we.DefaultWeTheme
import com.laomuji888.compose.core.ui.we.WeTheme

@Composable
fun WeTableRowOutline(
    modifier: Modifier = Modifier,
    color: Color = WeTheme.colorScheme.outline,
    height: Dp = (-1).dp,
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.FULL,
) {
    when (weTableRowOutlineType) {
        WeTableRowOutlineType.NONE -> {

        }

        WeTableRowOutlineType.FULL -> {
            Spacer(
                modifier = modifier
                    .height(if (height != (-1).dp) height else WeTheme.dimens.outlineHeight)
                    .fillMaxWidth()
                    .background(color)
            )
        }

        WeTableRowOutlineType.SPLIT -> {
            Spacer(
                modifier = modifier
                    .height(if (height != (-1).dp) height else WeTheme.dimens.outlineSplitHeight)
                    .fillMaxWidth()
                    .background(color)
            )
        }

        WeTableRowOutlineType.PADDING_HORIZONTAL -> {
            Spacer(
                modifier = modifier
                    .height(if (height != (-1).dp) height else WeTheme.dimens.outlineHeight)
                    .fillMaxWidth()
                    .padding(horizontal = WeTheme.dimens.tableRowPaddingHorizontal)
                    .background(color)
            )
        }

        WeTableRowOutlineType.PADDING_START -> {
            Spacer(
                modifier = modifier
                    .height(if (height != (-1).dp) height else WeTheme.dimens.outlineHeight)
                    .fillMaxWidth()
                    .padding(start = WeTheme.dimens.tableRowPaddingHorizontal)
                    .background(color)
            )
        }
    }
}

enum class WeTableRowOutlineType {
    NONE,
    FULL,
    PADDING_HORIZONTAL,
    PADDING_START,
    SPLIT,
}

@PreviewLightDark
@Composable
fun PreviewWeTableRowOutline() {
    DefaultWeTheme {
        Column {
            WeTableRow(
                weTableRowOutlineType = WeTableRowOutlineType.NONE
            )
            WeTableRow(
                weTableRowOutlineType = WeTableRowOutlineType.FULL
            )
            WeTableRow(
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableRow(
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_START
            )
            WeTableRow(
                weTableRowOutlineType = WeTableRowOutlineType.SPLIT
            )
        }
    }
}
