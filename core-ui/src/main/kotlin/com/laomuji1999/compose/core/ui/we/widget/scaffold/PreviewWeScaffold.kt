package com.laomuji1999.compose.core.ui.we.widget.scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.laomuji1999.compose.core.ui.theme.QuicklyTheme
import com.laomuji1999.compose.core.ui.we.widget.outline.WeOutline
import com.laomuji1999.compose.core.ui.we.widget.outline.WeOutlineType
import com.laomuji1999.compose.core.ui.we.widget.topbar.WeTopBar

@Preview
@Composable
fun PreviewWeScaffold() {
    QuicklyTheme {
        WeScaffold(topBar = {
            WeTopBar(title = "WeScaffold", onBackClick = {}, actions = {})
            WeOutline(
                weOutlineType = WeOutlineType.Full
            )
        }, bottomBar = {

        }, content = {

        })
    }
}