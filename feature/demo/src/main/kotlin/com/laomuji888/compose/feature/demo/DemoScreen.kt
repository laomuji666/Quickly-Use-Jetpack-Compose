package com.laomuji888.compose.feature.demo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.laomuji888.compose.core.logic.common.Log
import com.laomuji888.compose.core.ui.isPreview
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.icons.Device
import com.laomuji888.compose.core.ui.we.icons.Feature
import com.laomuji888.compose.core.ui.we.icons.Ui
import com.laomuji888.compose.core.ui.we.icons.WeIcons
import com.laomuji888.compose.core.ui.we.widget.bottombar.WeBottomBar
import com.laomuji888.compose.core.ui.we.widget.bottombar.WeBottomBarItem
import com.laomuji888.compose.core.ui.we.widget.scaffold.WeScaffold
import com.laomuji888.compose.core.ui.we.widget.topbar.WeTopBar
import com.laomuji888.compose.feature.demo.device.DeviceDemoScreen
import com.laomuji888.compose.feature.demo.feature.FeatureDemoScreen
import com.laomuji888.compose.feature.demo.ui.UiDemoScreen
import com.laomuji888.compose.res.R
import kotlinx.coroutines.launch

@Composable
fun DemoScreen(
    onDateClick: () -> Unit,
    onNestedScrollConnectionScreenClick: () -> Unit,
    onNestedScrollDispatcherScreenClick: () -> Unit,
    onPainterScreenClick: () -> Unit,
    onFirebaseClick: () -> Unit,
    onHttpClick: () -> Unit,
    onAiChatClick: () -> Unit,
    onBiometricScreenClick: () -> Unit,
    onYoutubeDLClick: () -> Unit,
    onWebViewClick: () -> Unit,
    onLanguageClick: () -> Unit,
) {
    //生命周期日志,在进入HelloScreen时开始记录,离开HelloScreen时停止记录.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    Log.debug("tag_demo_screen", "ON_START")
                }

                Lifecycle.Event.ON_STOP -> {
                    Log.debug("tag_demo_screen", "ON_STOP")
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
    val pagerState = rememberPagerState(
        initialPage = DemoSelectEnum.FEATURE.ordinal,
        pageCount = { DemoSelectEnum.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    WeScaffold(
        topBar = {
            WeTopBar(
                title = when (DemoSelectEnum.entries[pagerState.currentPage]) {
                    DemoSelectEnum.FEATURE -> stringResource(id = R.string.string_demo_screen_navigation_feature)
                    DemoSelectEnum.DEVICE -> stringResource(id = R.string.string_demo_screen_navigation_device)
                    DemoSelectEnum.UI -> stringResource(id = R.string.string_demo_screen_navigation_ui)
                }
            )
        },
        bottomBar = {
            DemoScreenBottomBar(
                selectedEnum = DemoSelectEnum.entries[pagerState.currentPage],
                onSelectedEnumChange = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(it.ordinal)
                    }
                }
            )
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = if (isPreview()) 0 else DemoSelectEnum.entries.size
        ) {
            when (DemoSelectEnum.entries[it]) {
                DemoSelectEnum.FEATURE -> FeatureDemoScreen(
                    onFirebaseClick = onFirebaseClick,
                    onHttpClick = onHttpClick,
                    onAiChatClick = onAiChatClick,
                    onBiometricScreenClick = onBiometricScreenClick,
                    onYoutubeDLClick = onYoutubeDLClick,
                    onWebViewClick = onWebViewClick,
                    onLanguageClick = onLanguageClick,
                )

                DemoSelectEnum.DEVICE -> DeviceDemoScreen()
                DemoSelectEnum.UI -> UiDemoScreen(
                    onDateClick = onDateClick,
                    onNestedScrollConnectionScreenClick = onNestedScrollConnectionScreenClick,
                    onNestedScrollDispatcherScreenClick = onNestedScrollDispatcherScreenClick,
                    onPainterScreenClick = onPainterScreenClick
                )
            }
        }
    }
}

@Composable
private fun DemoScreenBottomBar(
    selectedEnum: DemoSelectEnum,
    onSelectedEnumChange: (DemoSelectEnum) -> Unit,
) {
    WeBottomBar {
        WeBottomBarItem(
            unSelectImageVector = WeIcons.Feature,
            title = stringResource(id = R.string.string_demo_screen_navigation_feature),
            selected = selectedEnum == DemoSelectEnum.FEATURE,
            onClick = {
                onSelectedEnumChange(DemoSelectEnum.FEATURE)
            }
        )
        WeBottomBarItem(
            unSelectImageVector = WeIcons.Device,
            title = stringResource(id = R.string.string_demo_screen_navigation_device),
            selected = selectedEnum == DemoSelectEnum.DEVICE,
            onClick = {
                onSelectedEnumChange(DemoSelectEnum.DEVICE)
            }
        )
        WeBottomBarItem(
            unSelectImageVector = WeIcons.Ui,
            title = stringResource(id = R.string.string_demo_screen_navigation_ui),
            selected = selectedEnum == DemoSelectEnum.UI,
            onClick = {
                onSelectedEnumChange(DemoSelectEnum.UI)
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewDemoScreen() {
    QuicklyTheme {
        DemoScreen(
            onDateClick = {},
            onNestedScrollConnectionScreenClick = {},
            onNestedScrollDispatcherScreenClick = {},
            onPainterScreenClick = {},
            onFirebaseClick = {},
            onHttpClick = {},
            onAiChatClick = {},
            onBiometricScreenClick = {},
            onYoutubeDLClick = {},
            onWebViewClick = {},
            onLanguageClick = {},
        )
    }
}