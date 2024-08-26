package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji666.compose.core.logic.util.Toast
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.TopBarAdd
import com.laomuji666.compose.core.ui.we.icons.TopBarSearch
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTopBar
import com.laomuji666.compose.core.ui.we.widget.WeTopBarAction
import com.laomuji666.compose.core.ui.we.widget.WeTopBarActionSpace
import com.laomuji666.compose.res.R

@Composable
fun HelloScreen(
    viewModel: HelloViewModel = hiltViewModel(),
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit
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
        }
    )
}

@Composable
private fun HelloScreenUi(
    uiState: HelloUiState,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit
){
    Column(
        modifier = Modifier.background(WeTheme.weColorScheme.backgroundColor).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeTopBar(
            title = "标题",
            actions = {
                WeTopBarAction(
                    imageVector = WeIcons.TopBarSearch
                )
                WeTopBarActionSpace()
                WeTopBarAction(
                    imageVector = WeIcons.TopBarAdd
                )
            },
            onBackClick = {}
        )
        WeTableClickRow(
            title = uiState.helloText,
            showOutLine = true
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_firebase_demo),
            onClick = onFirebaseClick,
            showOutLine = true
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_http_demo),
            onClick = onHttpClick,
            showOutLine = true
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_google_login_demo),
            onClick = onGoogleLoginClick,
            showOutLine = false
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
            onGoogleLoginClick = {}
        )
    }
}