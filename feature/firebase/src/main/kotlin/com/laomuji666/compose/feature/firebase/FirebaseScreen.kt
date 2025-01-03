package com.laomuji666.compose.feature.firebase

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.laomuji666.compose.core.ui.extension.isForeverDenied
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonColor
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.res.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FirebaseScreen(
    viewModel: FirebaseScreenViewModel = hiltViewModel(),
    onBackClick:()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    @SuppressLint("InlinedApi")
    val postNotificationPermissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )
    FirebaseScreenUi(
        uiState = uiState,
        onBackClick = onBackClick,
        logEventClick = {
            viewModel.onAction(FirebaseScreenAction.OnClickLogEvent)
        },
        updatePushToken = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                if(postNotificationPermissionState.status.isGranted){
                    viewModel.onAction(FirebaseScreenAction.UpdatePushToken)
                }else{
                    if (postNotificationPermissionState.status.isForeverDenied()) {
                        //永久拒绝了权限,需要打开设置页面手动授权
                    } else {
                        postNotificationPermissionState.launchPermissionRequest()
                    }
                }
            }else{
                viewModel.onAction(FirebaseScreenAction.UpdatePushToken)
            }
        },
        testCrashlytics = {
            viewModel.onAction(FirebaseScreenAction.TestCrashlytics)
        }
    )
}

@Composable
private fun FirebaseScreenUi(
    uiState: FirebaseScreenUiState,
    onBackClick: ()->Unit,
    logEventClick: () -> Unit,
    updatePushToken:()->Unit,
    testCrashlytics:()->Unit
){
    WeScaffold(
        topBar = {
            WeTopNavigationBar(
                title = stringResource(id = R.string.string_demo_screen_firebase_demo),
                onBackClick = onBackClick
            )
        }
    ){
        Spacer(modifier = Modifier.height(20.dp))
        FirebaseScreenSlot(text = stringResource(id = R.string.string_firebase_screen_log_event), onClick = logEventClick)
        Spacer(modifier = Modifier.height(20.dp))
        FirebasePermissionSlot(
            pushToken = uiState.pushToken,
            updatePushToken = updatePushToken
        )
        Spacer(modifier = Modifier.height(20.dp))
        FirebaseScreenSlot(text = stringResource(id = R.string.string_firebase_screen_crush), onClick = testCrashlytics)

    }
}

@Composable
private fun FirebaseScreenSlot(
    text:String,
    onClick:()->Unit
){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        WeButton(weButtonType = WeButtonType.BIG, weButtonColor = WeButtonColor.PRIMARY, text = text, onClick = onClick)
    }
}

@SuppressLint("InlinedApi")
@Composable
private fun FirebasePermissionSlot(
    pushToken:String,
    updatePushToken:()->Unit
){
    if(pushToken.isNotEmpty()){
        Text(text = pushToken, color = WeTheme.colorScheme.fontColor90)
    }else{
        FirebaseScreenSlot(text = stringResource(id = R.string.string_firebase_screen_notification), onClick = {
            updatePushToken()
        })
    }
}

@Preview
@Composable
fun PreviewFirebaseScreen(){
    QuicklyTheme {
        FirebaseScreenUi(
            uiState = FirebaseScreenUiState(),
            onBackClick = {},
            logEventClick = {},
            updatePushToken = {},
            testCrashlytics = {}
        )
    }
}