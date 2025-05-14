package com.laomuji888.compose.core.ui.we.widget.topbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laomuji888.compose.core.ui.we.WeTheme

@Composable
fun WeTopBarActionSpace() {
    Spacer(modifier = Modifier.width(WeTheme.dimens.topBarActionSpace))
}