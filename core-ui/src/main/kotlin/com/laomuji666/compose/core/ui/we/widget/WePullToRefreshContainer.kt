package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WePullToRefreshContainer(
    state: PullToRefreshState,
    modifier: Modifier = Modifier,
    indicator: @Composable (PullToRefreshState) -> Unit = {},
    onRefresh: ()->Unit = {}
){
    LaunchedEffect(state.isRefreshing) {
        if(state.isRefreshing){
            onRefresh()
            state.endRefresh()
        }
    }

    val density = LocalDensity.current.density
    var pullToRefreshHeight by remember { mutableStateOf(0.dp) }
    val animHeight by animateDpAsState(
        targetValue = pullToRefreshHeight,
        animationSpec = if(pullToRefreshHeight == 0.dp) spring() else tween(0),
        label = ""
    )
    LaunchedEffect(state.verticalOffset) {
        pullToRefreshHeight = (state.verticalOffset / density).dp
    }

    Box(
        modifier = modifier
            .height(if (pullToRefreshHeight == 0.dp) animHeight else pullToRefreshHeight)
            .graphicsLayer {
                translationY = state.verticalOffset - size.height
            }
    ) {
        indicator(state)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun PreviewWePullToRefreshContainer(){
    val pullToRefreshState = rememberPullToRefreshState()
    val items = listOf("A","B","C")
    DefaultWeTheme {
        Column(
            modifier = Modifier
                .background(WeTheme.colorScheme.background)
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
                .fillMaxSize(),
        ) {
            WePullToRefreshContainer(
                state = pullToRefreshState,
                indicator = {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(WeTheme.colorScheme.fontColor90)
                    )
                }
            )
            LazyColumn{
                items(items){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(WeTheme.colorScheme.error)) {
                        Text(text = it, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}