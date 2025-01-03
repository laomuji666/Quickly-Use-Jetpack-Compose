package com.laomuji666.compose.feature.chat.me

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutline
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeTableSwitchRow
import com.laomuji666.compose.res.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MeScreen(
    viewModel: MeScreenViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    @SuppressLint("InlinedApi")
    val postNotificationPermissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )
    MeScreenUi(
        enableNotification = uiState.enableNotification,
        onEnableNotificationClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                if(postNotificationPermissionState.status.isGranted){
                    viewModel.onAction(MeScreenAction.SwitchEnableNotification)
                }else{
                    if(postNotificationPermissionState.status.isForeverDenied()){
                        //永久拒绝了权限,需要打开设置页面手动授权
                    }else{
                        postNotificationPermissionState.launchPermissionRequest()
                    }
                }
            }else {
                viewModel.onAction(MeScreenAction.SwitchEnableNotification)
            }
        }
    )
}

@Composable
fun MeScreenUi(
    enableNotification:Boolean,
    onEnableNotificationClick:()->Unit
){
    Column(
        modifier = Modifier
            .background(WeTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .background(WeTheme.colorScheme.tableRowBackground)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Row(modifier = Modifier
                .height(95.dp)
                .padding(WeTheme.dimens.listPaddingHorizontal)) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxHeight()
                )
                Spacer(modifier = Modifier.width(WeTheme.dimens.listPaddingHorizontal))
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = WeTheme.dimens.listPaddingHorizontal / 4)) {
                    Text(
                        text = stringResource(id = R.string.string_ai_chat_me_screen_we_chat_name),
                        style = WeTheme.typography.emTitle,
                        color = WeTheme.colorScheme.fontColor90
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.string_ai_chat_me_screen_we_chat_number_format, stringResource(id = R.string.string_ai_chat_me_screen_we_chat_number)),
                        style = WeTheme.typography.desc,
                        color = WeTheme.colorScheme.fontColor50
                    )
                }
            }
            WeTableRowOutline(
                weTableRowOutlineType = WeTableRowOutlineType.SPLIT
            )
        }
        WeTableSwitchRow(
            title = stringResource(id = R.string.string_ai_chat_me_screen_enable_notification),
            checked = enableNotification,
            onClick = onEnableNotificationClick
        )
    }
}


@Preview
@Composable
fun PreviewMeScreenUi(){
    QuicklyTheme {
        MeScreenUi(
            enableNotification = true,
            onEnableNotificationClick = {}
        )
    }
}