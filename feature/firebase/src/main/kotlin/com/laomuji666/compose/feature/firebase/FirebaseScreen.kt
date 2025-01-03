package com.laomuji666.compose.feature.firebase

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.common.Toast
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonColor
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.res.R

@Composable
fun FirebaseScreen(
    viewModel: FirebaseScreenViewModel = hiltViewModel(),
    onBackClick:()->Unit
){
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if(isGranted){
                viewModel.onAction(FirebaseScreenAction.UpdatePushToken)
            }else{
                if(!ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.POST_NOTIFICATIONS)){
                    Toast.showText(context = context, resId = R.string.string_permission_notification_forever_denied)
                }
            }
        }
    )
    FirebaseScreenUi(
        uiState = uiState,
        onBackClick = onBackClick,
        logEventClick = {
            viewModel.onAction(FirebaseScreenAction.OnClickLogEvent)
        },
        updatePushToken = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
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