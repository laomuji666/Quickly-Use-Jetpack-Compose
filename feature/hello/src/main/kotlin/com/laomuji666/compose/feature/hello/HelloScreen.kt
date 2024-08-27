package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji666.compose.core.logic.util.Toast
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.icons.Example
import com.laomuji666.compose.core.ui.we.icons.TopBarAdd
import com.laomuji666.compose.core.ui.we.icons.TopBarSearch
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.icons.Widget
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBarItem
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
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
    var selected by rememberSaveable{ mutableStateOf(HelloSelectEnum.EXAMPLE) }
    WeScaffold(
        topBar = {
            WeTopBar(
                actions = {
                    WeTopBarAction(
                        imageVector = WeIcons.TopBarSearch
                    )
                    WeTopBarActionSpace()
                    WeTopBarAction(
                        imageVector = WeIcons.TopBarAdd
                    )
                }
            )
        },
        bottomBar = {
            WeNavigationBar {
                WeNavigationBarItem(
                    imageVector = WeIcons.Example,
                    title = stringResource(id = R.string.string_hello_screen_navigation_example),
                    selected = selected == HelloSelectEnum.EXAMPLE,
                    onClick = { selected = HelloSelectEnum.EXAMPLE }
                )
                WeNavigationBarItem(
                    imageVector = WeIcons.Widget,
                    title = stringResource(id = R.string.string_hello_screen_navigation_widget),
                    selected = selected == HelloSelectEnum.WIDGET,
                    onClick = { selected = HelloSelectEnum.WIDGET }
                )
            }
        }
    ) {
        when(selected){
            HelloSelectEnum.EXAMPLE -> {
                Example(
                    helloText = uiState.helloText,
                    onFirebaseClick = onFirebaseClick,
                    onHttpClick = onHttpClick,
                    onGoogleLoginClick = onGoogleLoginClick
                )
            }
            HelloSelectEnum.WIDGET -> {

            }
        }
    }
}

@Composable
private fun Example(
    helloText: String,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit
){
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