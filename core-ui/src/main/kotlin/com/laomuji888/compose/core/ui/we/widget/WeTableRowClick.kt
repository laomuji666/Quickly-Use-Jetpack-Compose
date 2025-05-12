package com.laomuji888.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.we.DefaultWeTheme
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.icons.Arrow
import com.laomuji888.compose.core.ui.we.icons.WeIcons

@Composable
fun WeTableRowClick(
    title: String,
    summary: String? = null,
    summaryInBottom: Boolean = false,
    onClick: () -> Unit = {},
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.NONE
) {
    WeTableRow(
        start = {
            if (summaryInBottom) {
                Column {
                    Text(
                        text = title,
                        style = WeTheme.typography.title,
                        color = WeTheme.colorScheme.fontColorHeavy
                    )
                    summary?.let {
                        Text(
                            text = it,
                            style = WeTheme.typography.desc,
                            color = WeTheme.colorScheme.fontColorLight
                        )
                    }
                }
            } else {
                Text(
                    text = title,
                    style = WeTheme.typography.title,
                    color = WeTheme.colorScheme.fontColorHeavy
                )
            }
        },
        end = {
            if (!summaryInBottom) {
                summary?.let {
                    Text(
                        text = it,
                        style = WeTheme.typography.title,
                        color = WeTheme.colorScheme.fontColorLight
                    )
                    Spacer(modifier = Modifier.width(WeTheme.dimens.tableRowInnerPaddingHorizontal))
                }
            }
            Image(
                imageVector = WeIcons.Arrow,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(WeTheme.colorScheme.fontColorLight),
                modifier = Modifier.height(WeTheme.dimens.actionIconSize)
            )
        },
        weTableRowType = if (summaryInBottom) WeTableRowType.DOUBLE else WeTableRowType.SINGLE,
        onClick = onClick,
        weTableRowOutlineType = weTableRowOutlineType
    )
}

@PreviewLightDark
@Composable
fun PreviewWeTableRowClick1() {
    DefaultWeTheme {
        WeTableRowClick(
            title = "双行标题",
            summary = "详细信息",
            summaryInBottom = true
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTableRowClick2() {
    DefaultWeTheme {
        WeTableRowClick(
            title = "单行标题",
            summary = "详细信息"
        )
    }
}