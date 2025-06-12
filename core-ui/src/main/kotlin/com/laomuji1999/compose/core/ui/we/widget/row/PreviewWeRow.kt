package com.laomuji1999.compose.core.ui.we.widget.row

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji1999.compose.core.ui.theme.QuicklyTheme
import com.laomuji1999.compose.core.ui.we.widget.outline.WeOutlineType

@PreviewLightDark
@Composable
fun PreviewWeRow() {
    QuicklyTheme {
        Column {
            WeRow(
                weOutlineType = WeOutlineType.None
            )
            WeRow(
                weOutlineType = WeOutlineType.Full
            )
            WeRow(
                weOutlineType = WeOutlineType.PaddingHorizontal
            )
            WeRow(
                weOutlineType = WeOutlineType.PaddingStart
            )
            WeRow(
                weOutlineType = WeOutlineType.Split
            )
        }
    }
}
