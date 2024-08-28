package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeScaffold(
    topBar: @Composable ()->Unit = {},
    topBarActionMenu: @Composable ()->Unit = {},
    bottomBar: @Composable ()->Unit = {},
    content: @Composable ColumnScope.()->Unit
){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .background(WeTheme.weColorScheme.backgroundColor)
                .fillMaxSize()
        ) {
            topBar()
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                content()
            }
            bottomBar()
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(
                    top = WeTheme.weDimens.topBarHeightDp,
                    end = WeTheme.weDimens.paddingHorizontalDp / 2
                )
        ) {
            topBarActionMenu()
        }
    }
}