package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji666.compose.core.logic.common.Log
import com.laomuji666.compose.core.logic.common.Toast
import com.laomuji666.compose.core.ui.launcher.selectMobileLauncher
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.icons.Example
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.icons.Widget
import com.laomuji666.compose.core.ui.we.widget.WeBottomNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeBottomNavigationBarItem
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.res.R
import kotlinx.coroutines.launch

@Composable
fun HelloScreen(
    viewModel: HelloViewModel = hiltViewModel(),
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit,
    onAiChatClick:()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val selectMobile = selectMobileLauncher(
        onSuccess = {
            Toast.showText(context, it)
        },
        onFail = {
            Toast.showText(context, "...")
        }
    )
    HelloScreenUi(
        uiState = uiState,
        onFirebaseClick = onFirebaseClick,
        onHttpClick = onHttpClick,
        onGoogleLoginClick = {
            GoogleAuthenticate().requestLogin(
                activityContext = context,
                onSuccess = { email, idToken ->
                    Toast.showText(context, "$email $idToken")
                },
                onFail = {
                    Toast.showText(context, "...")
                }
            )
        },
        onSelectMobileClick = {
            selectMobile()
        },
        onAiChatClick = onAiChatClick,
        onSwitchAppLogoClick = {
            viewModel.switchAppLogo(context)
        }
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
    uiState: HelloUiState,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit,
    onSelectMobileClick:()->Unit,
    onAiChatClick:()->Unit,
    onSwitchAppLogoClick:()->Unit,
){
    val pagerState = rememberPagerState(
        initialPage = HelloSelectEnum.EXAMPLE.ordinal,
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
            beyondViewportPageCount = 3
        ) {
            if(it == HelloSelectEnum.EXAMPLE.ordinal){
                ExampleScreen(
                    helloText = uiState.helloText,
                    enableSwitchAppLogo = uiState.enableSwitchAppLogo,
                    onSwitchAppLogoClick = onSwitchAppLogoClick,
                    onFirebaseClick = onFirebaseClick,
                    onHttpClick = onHttpClick,
                    onGoogleLoginClick = onGoogleLoginClick,
                    onSelectMobileClick = onSelectMobileClick,
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
            uiState = HelloUiState(),
            onFirebaseClick = {},
            onHttpClick = {},
            onGoogleLoginClick = {},
            onSelectMobileClick = {},
            onAiChatClick = {},
            onSwitchAppLogoClick = {}
        )
    }
}