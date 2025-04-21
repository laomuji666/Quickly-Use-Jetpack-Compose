package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.clickableDebounce
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableRow(
    modifier: Modifier = Modifier,
    start: @Composable RowScope.() -> Unit = {},
    center: @Composable RowScope.() -> Unit = { Spacer(modifier = Modifier.weight(1f)) },
    end: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = WeTheme.colorScheme.tableRowBackground,
    clickTimeout: Long = 200L,
    onClick: () -> Unit = {},
    weTableRowType: WeTableRowType = WeTableRowType.SINGLE,
    outlineModifier: Modifier = Modifier,
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
) {
    val rowHeight = when (weTableRowType) {
        WeTableRowType.SINGLE -> if (weTableRowOutlineType == WeTableRowOutlineType.SPLIT) {
            WeTheme.dimens.listSingleRowHeight + WeTheme.dimens.outlineSplitHeight
        } else {
            WeTheme.dimens.listSingleRowHeight
        }

        WeTableRowType.DOUBLE -> if (weTableRowOutlineType == WeTableRowOutlineType.SPLIT) {
            WeTheme.dimens.listDoubleRowHeight + WeTheme.dimens.outlineSplitHeight
        } else {
            WeTheme.dimens.listDoubleRowHeight
        }
    }
    Column(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .height(rowHeight)
            .clickableDebounce(onClick = onClick, timeout = clickTimeout)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .weight(1f)
                .padding(horizontal = WeTheme.dimens.listPaddingHorizontal),
            verticalAlignment = Alignment.CenterVertically
        ) {
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

enum class WeTableRowType {
    SINGLE,
    DOUBLE
}

@PreviewLightDark
@Composable
fun PreviewWeTableRow() {
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
