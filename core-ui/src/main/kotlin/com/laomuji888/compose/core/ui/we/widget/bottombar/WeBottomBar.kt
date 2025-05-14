package com.laomuji888.compose.core.ui.we.widget.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutline
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutlineType

@Composable
fun WeBottomBar(
    content: @Composable RowScope.() -> Unit
) {
    Column {
        WeOutline(
            weOutlineType = WeOutlineType.Full
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WeTheme.colorScheme.bottomBarBackground)
                .height(WeTheme.dimens.bottomBarHeight)
        ) {
            content()
        }
    }
}