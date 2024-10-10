package com.laomuji666.compose.feature.hello

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeTableSwitchRow
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.res.R

@Composable
fun ExampleScreen(
    helloText: String,
    enableSwitchAppLogo: Boolean,
    onSwitchAppLogoClick:()->Unit,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit,
    onSelectMobileClick:()->Unit,
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
            title = stringResource(id = R.string.string_hello_screen_select_mobile_demo),
            onClick = onSelectMobileClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_hello_screen_ai_chat),
            onClick = onAiChatClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableSwitchRow(
            title = stringResource(id = R.string.string_hello_screen_switch_app_logo),
            checked = enableSwitchAppLogo,
            onClick = onSwitchAppLogoClick
        )
    }
}

@Preview
@Composable
fun PreviewExampleScreen(){
    QuicklyTheme {
        ExampleScreen(
            helloText = "",
            onFirebaseClick = {},
            onHttpClick = {},
            onGoogleLoginClick = {},
            onSelectMobileClick = {},
            onAiChatClick = {},
            enableSwitchAppLogo = false,
            onSwitchAppLogoClick = {}
        )
    }
}
