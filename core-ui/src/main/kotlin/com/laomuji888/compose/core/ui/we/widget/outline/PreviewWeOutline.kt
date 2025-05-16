package com.laomuji888.compose.core.ui.we.widget.outline

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.widget.row.WeRow

@PreviewLightDark
@Composable
fun PreviewWeOutline() {
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
