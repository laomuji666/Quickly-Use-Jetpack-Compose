package com.laomuji888.compose.core.ui.we.widget.radio

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutlineType


@PreviewLightDark
@Composable
fun PreviewWeRadio() {
    var currentItem by remember { mutableIntStateOf(0) }
    val titleList = listOf("item1", "item2", "item3", "item4")
    QuicklyTheme {
        Column {
            titleList.forEachIndexed { index, title ->
                WeRadio(
                    title = title,
                    checked = index == currentItem,
                    weOutlineType = if (index == titleList.lastIndex) WeOutlineType.None else WeOutlineType.PaddingHorizontal,
                    onClick = { currentItem = index })
            }
        }
    }
}
