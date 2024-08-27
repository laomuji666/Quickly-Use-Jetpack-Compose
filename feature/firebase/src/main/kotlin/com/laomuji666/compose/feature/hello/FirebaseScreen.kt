package com.laomuji666.compose.feature.hello

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonSizeType
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTopBar
import com.laomuji666.compose.res.R

@Composable
fun FirebaseScreen(
    viewModel: FirebaseViewModel = hiltViewModel(),
    onBackClick:()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FirebaseScreenUi(
        uiState = uiState,
        onBackClick = onBackClick,
        logEventClick = {
            viewModel.logEventClick()
        },
        updatePushToken = {
            viewModel.updatePushToken()
        },
        testCrashlytics = {
            viewModel.testCrashlytics()
        }
    )
}

@Composable
private fun FirebaseScreenUi(
    uiState: FirebaseUiState,
    onBackClick: ()->Unit,
    logEventClick: () -> Unit,
    updatePushToken:()->Unit,
    testCrashlytics:()->Unit
){
    Column(
        modifier = Modifier
            .background(WeTheme.weColorScheme.backgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeScaffold(
            topBar = {
                WeTopBar(
                    title = stringResource(id = R.string.string_hello_screen_firebase_demo),
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
}

@Composable
private fun FirebaseScreenSlot(
    text:String,
    onClick:()->Unit
){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        WeButton(weButtonType = WeButtonType.PRIMARY, weButtonSizeType = WeButtonSizeType.BIG, text = text, onClick = onClick)
    }
}

@SuppressLint("InlinedApi")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun FirebasePermissionSlot(
    pushToken:String,
    updatePushToken:()->Unit
){
    var isRequestPermission by rememberSaveable { mutableStateOf(false) }
    var hasPermission by rememberSaveable { mutableStateOf(false) }
    if(!LocalView.current.isInEditMode){
        val postNotificationPermissionState = rememberPermissionState(
            android.Manifest.permission.POST_NOTIFICATIONS
        )
        hasPermission = postNotificationPermissionState.status.isGranted
        if(hasPermission){
            LaunchedEffect(Unit) {
                updatePushToken()
            }
        }
        if(isRequestPermission){
            isRequestPermission = false
            if (!postNotificationPermissionState.status.isGranted && postNotificationPermissionState.status.shouldShowRationale) {
                //永久拒绝了权限,需要打开设置页面手动授权
            } else {
                LaunchedEffect(Unit) {
                    postNotificationPermissionState.launchPermissionRequest()
                }
            }
        }
    }
    if(hasPermission){
        Text(text = pushToken, color = WeTheme.weColorScheme.onBackgroundColor)
    }else{
        FirebaseScreenSlot(text = stringResource(id = R.string.string_firebase_screen_notification), onClick = {
            isRequestPermission = true
        })
    }
}

@Preview
@Composable
fun PreviewFirebaseScreen(){
    QuicklyTheme {
        FirebaseScreenUi(
            uiState = FirebaseUiState(),
            onBackClick = {},
            logEventClick = {},
            updatePushToken = {},
            testCrashlytics = {}
        )
    }
}