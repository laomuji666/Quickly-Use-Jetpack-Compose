package com.laomuji666.compose.feature.hello

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.laomuji666.compose.core.logic.common.Log
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.icons.Example
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.icons.Widget
import com.laomuji666.compose.core.ui.we.widget.WeBottomNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeBottomNavigationBarItem
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.res.R
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@Composable
fun HelloScreen(
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit,
    onAiChatClick:()->Unit
){
    HelloScreenUi(
        onFirebaseClick = onFirebaseClick,
        onHttpClick = onHttpClick,
        onAiChatClick = onAiChatClick,
    )

    //生命周期日志,在进入HelloScreen时开始记录,离开HelloScreen时停止记录.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _,event->
            when(event){
                Lifecycle.Event.ON_START -> {
                    Log.debug("tag_hello_screen", "ON_START")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.debug("tag_hello_screen", "ON_STOP")
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
}

@Composable
private fun HelloScreenUi(
    initialPage:Int = HelloSelectEnum.EXAMPLE.ordinal,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onAiChatClick:()->Unit,
){
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { HelloSelectEnum.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    WeScaffold(
        bottomBar = {
            WeBottomNavigationBar {
                WeBottomNavigationBarItem(
                    unSelectImageVector = WeIcons.Example,
                    title = stringResource(id = R.string.string_hello_screen_navigation_example),
                    selected = pagerState.currentPage == HelloSelectEnum.EXAMPLE.ordinal,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(HelloSelectEnum.EXAMPLE.ordinal)
                        }
                    }
                )
                WeBottomNavigationBarItem(
                    unSelectImageVector = WeIcons.Widget,
                    title = stringResource(id = R.string.string_hello_screen_navigation_widget),
                    selected = pagerState.currentPage == HelloSelectEnum.WIDGET.ordinal,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(HelloSelectEnum.WIDGET.ordinal)
                        }
                    }
                )
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = 3,
            userScrollEnabled = false
        ) {
            if(it == HelloSelectEnum.EXAMPLE.ordinal){
                ExampleScreen(
                    onFirebaseClick = onFirebaseClick,
                    onHttpClick = onHttpClick,
                    onAiChatClick = onAiChatClick
                )
            }
            if(it == HelloSelectEnum.WIDGET.ordinal){
                WidgetScreen()
            }
        }
    }
}

@Preview
@Composable
fun PreviewHelloScreenUi(){
    QuicklyTheme {
        HelloScreenUi(
            initialPage = HelloSelectEnum.WIDGET.ordinal,
            onFirebaseClick = {},
            onHttpClick = {},
            onAiChatClick = {},
        )
    }
}