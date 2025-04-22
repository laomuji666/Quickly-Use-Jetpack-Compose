package com.laomuji666.compose.feature.demo.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.laomuji666.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji666.compose.core.logic.common.Toast
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.res.R

@Composable
fun FeatureDemoScreen(
    onFirebaseClick: () -> Unit,
    onHttpClick: () -> Unit,
    onAiChatClick: () -> Unit,
    onBiometricScreenClick: () -> Unit,
    onYoutubeDLClick: () -> Unit,
    onWebViewClick: () -> Unit,
    onLanguageClick: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_firebase_demo),
            onClick = onFirebaseClick,
            weTableRowOutlineType = WeTableRowOutlineType.FULL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_http_demo),
            onClick = onHttpClick,
            weTableRowOutlineType = WeTableRowOutlineType.FULL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_ai_chat),
            onClick = onAiChatClick,
            weTableRowOutlineType = WeTableRowOutlineType.FULL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_biometric),
            onClick = onBiometricScreenClick,
            weTableRowOutlineType = WeTableRowOutlineType.FULL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_google_login_demo),
            onClick = {
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
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_youtubedl_demo),
            onClick = onYoutubeDLClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_web_view_demo),
            onClick = onWebViewClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_language_screen_title),
            onClick = onLanguageClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
    }
}

@Preview
@Composable
private fun PreviewFeatureDemoScreen() {
    QuicklyTheme {
        WeScaffold {
            FeatureDemoScreen(
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
}