package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji666.compose.core.logic.util.Toast
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.icons.Example
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.icons.Widget
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBarItem
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
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
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    HelloScreenUi(
        uiState = uiState,
        onFirebaseClick = onFirebaseClick,
        onHttpClick = onHttpClick,
        onGoogleLoginClick = {
            GoogleAuthenticate().requestLogin(
                coroutineScope = coroutineScope,
                activityContext = context,
                onSuccess = { email, idToken ->
                    Toast.showText(context, "$email $idToken")
                },
                onFail = {
                    //登录失败或取消登录
                }
            )
        },
        onAiChatClick = onAiChatClick
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HelloScreenUi(
    uiState: HelloUiState,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit,
    onAiChatClick:()->Unit
){
    val pagerState = rememberPagerState(
        initialPage = HelloSelectEnum.EXAMPLE.ordinal,
        pageCount = { HelloSelectEnum.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    WeScaffold(
        bottomBar = {
            WeNavigationBar {
                WeNavigationBarItem(
                    unSelectImageVector = WeIcons.Example,
                    title = stringResource(id = R.string.string_hello_screen_navigation_example),
                    selected = pagerState.currentPage == HelloSelectEnum.EXAMPLE.ordinal,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(HelloSelectEnum.EXAMPLE.ordinal)
                        }
                    }
                )
                WeNavigationBarItem(
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
            modifier = Modifier.fillMaxSize()
        ) {
            if(it == HelloSelectEnum.EXAMPLE.ordinal){
                Example(
                    helloText = uiState.helloText,
                    onFirebaseClick = onFirebaseClick,
                    onHttpClick = onHttpClick,
                    onGoogleLoginClick = onGoogleLoginClick,
                    onAiChatClick = onAiChatClick
                )
            }
            if(it == HelloSelectEnum.WIDGET.ordinal){
                WidgetScreen()
            }
        }
    }
}

@Composable
private fun Example(
    helloText: String,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit,
    onAiChatClick:()->Unit
){
    WeScaffold(
        topBar = {
            WeTopNavigationBar(title = stringResource(id = R.string.string_hello_screen_navigation_example))
        }
    ) {
        WeTableClickRow(
            title = helloText,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_firebase_demo),
            onClick = onFirebaseClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_http_demo),
            onClick = onHttpClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_google_login_demo),
            onClick = onGoogleLoginClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_ai_chat),
            onClick = onAiChatClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
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
            onAiChatClick = {}
        )
    }
}