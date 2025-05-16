package com.laomuji888.compose.core.ui.we.widget.row

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
import androidx.compose.ui.unit.Dp
import com.laomuji888.compose.core.ui.clickableDebounce
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutline
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutlineType

@Composable
fun WeRow(
    modifier: Modifier = Modifier,
    start: @Composable RowScope.() -> Unit = {},
    center: @Composable RowScope.() -> Unit = { Spacer(modifier = Modifier.weight(1f)) },
    end: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = WeTheme.colorScheme.rowBackground,
    onClick: () -> Unit = {},
    weTableRowType: WeRowType = WeRowType.Single,
    weOutlineType: WeOutlineType = WeOutlineType.PaddingHorizontal,
    paddingHorizontal: Dp = WeTheme.dimens.rowPaddingHorizontal,
) {
    val rowHeight = when (weTableRowType) {
        WeRowType.Single -> WeTheme.dimens.rowSingleHeight
        WeRowType.Double -> WeTheme.dimens.rowDoubleHeight
    }
    Column(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .clickableDebounce(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(rowHeight)
                .padding(horizontal = paddingHorizontal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            start()
            center()
            end()
        }
        WeOutline(
            weOutlineType = weOutlineType
        )
    }
}