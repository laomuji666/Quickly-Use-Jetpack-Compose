package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeScaffold(
    topBar: @Composable ()->Unit = {},
    bottomBar: @Composable ()->Unit = {},
    scrollState: ScrollState = rememberScrollState(),
    content: @Composable ColumnScope.()->Unit
){
    Column(
        modifier = Modifier
            .background(WeTheme.weColorScheme.backgroundColor)
            .fillMaxSize()
    ) {
        topBar()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            content()
        }
        bottomBar()
    }
}