package com.laomuji888.compose.core.ui.we.widget.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.icons.Add
import com.laomuji888.compose.core.ui.we.icons.Search
import com.laomuji888.compose.core.ui.we.icons.WeIcons

@PreviewLightDark
@Composable
fun PreviewWeTopBar() {
    QuicklyTheme {
        WeTopBar(
            title = "标题",
            onBackClick = {},
            actions = {
                WeTopBarAction(
                    imageVector = WeIcons.Search
                )
                WeTopBarActionSpace()
                WeTopBarAction(
                    imageVector = WeIcons.Add
                )
            }
        )
    }
}