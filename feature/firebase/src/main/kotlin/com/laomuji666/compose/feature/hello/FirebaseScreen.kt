package com.laomuji666.compose.feature.hello

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.laomuji666.compose.core.ui.QuicklyTheme
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun FirebaseScreen(
    viewModel: FirebaseViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FirebaseScreenUi(
        uiState = uiState,
        logEventClick = {
            viewModel.logEventClick()
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun FirebaseScreenUi(
    uiState: FirebaseUiState,
    logEventClick: () -> Unit = {},
){
    @SuppressLint("InlinedApi")
    val postNotificationPermissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            FirebaseScreenSlot(text = "埋点", onClick = logEventClick)
            if (postNotificationPermissionState.status.isGranted) {
                Text(text = uiState.pushToken)
            }else{
                FirebaseScreenSlot(text = "申请通知权限", onClick = {
                    postNotificationPermissionState.launchPermissionRequest()
                })
            }
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

@Preview
@Composable
fun PreviewFirebaseScreen(){
    QuicklyTheme {
        FirebaseScreenUi(
            uiState = FirebaseUiState()
        )
    }
}