package com.laomuji1999.compose.core.ui.we.widget.click

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji1999.compose.core.ui.theme.QuicklyTheme

@PreviewLightDark
@Composable
fun PreviewWeClick1() {
    QuicklyTheme {
        WeClick(
            title = "双行标题", summary = "详细信息", summaryInBottom = true
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewWeClick2() {
    QuicklyTheme {
        WeClick(
            title = "单行标题", summary = "详细信息"
        )
    }
}