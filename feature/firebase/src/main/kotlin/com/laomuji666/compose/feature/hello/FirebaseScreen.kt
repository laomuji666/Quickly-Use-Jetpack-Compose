package com.laomuji666.compose.feature.hello

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.laomuji666.compose.core.ui.QuicklyTheme

@Composable
fun FirebaseScreen(
    viewModel: FirebaseViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FirebaseScreenUi(
        uiState = uiState,
        logEventClick = {
            viewModel.logEventClick()
        },
        updatePushToken = {
            viewModel.updatePushToken()
        }
    )
}

@Composable
private fun FirebaseScreenUi(
    uiState: FirebaseUiState,
    logEventClick: () -> Unit,
    updatePushToken:()->Unit
){
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            FirebaseScreenSlot(text = "埋点", onClick = logEventClick)
            FirebasePermissionSlot(
                pushToken = uiState.pushToken,
                updatePushToken = updatePushToken
            )
        }
    }
}

@Composable
private fun FirebaseScreenSlot(
    text:String,
    onClick:()->Unit
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = text)
    }
    Spacer(modifier = Modifier.height(10.dp))
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
                Toast.makeText(LocalContext.current, "请允许通知权限", Toast.LENGTH_SHORT).show()
            } else {
                LaunchedEffect(Unit) {
                    postNotificationPermissionState.launchPermissionRequest()
                }
            }
        }
    }
    if(hasPermission){
        Text(text = pushToken)
    }else{
        FirebaseScreenSlot(text = "申请通知权限", onClick = {
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
            logEventClick = {},
            updatePushToken = {}
        )
    }
}