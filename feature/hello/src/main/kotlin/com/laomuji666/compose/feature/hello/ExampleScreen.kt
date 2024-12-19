package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.laomuji666.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji666.compose.core.logic.common.Toast
import com.laomuji666.compose.core.ui.extension.isForeverDenied
import com.laomuji666.compose.core.ui.launcher.selectMobileLauncher
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.view.DragList
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeTableSwitchRow
import com.laomuji666.compose.core.ui.we.widget.WeToast
import com.laomuji666.compose.core.ui.we.widget.WeToastType
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.launcher.openAlbum
import com.laomuji666.compose.launcher.openCamera
import com.laomuji666.compose.launcher.openContact
import com.laomuji666.compose.res.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExampleScreen(
    viewModel: ExampleScreenViewModel = hiltViewModel(),
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit,
    onAiChatClick:()->Unit,
    onDateClick:()->Unit,
    onNestedScrollConnectionScreenClick:()->Unit,
    onNestedScrollDispatcherScreenClick:()->Unit,
    onBiometricScreenClick:()->Unit,
    onPainterScreenClick:()->Unit,
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if(uiState.isLoading){
        WeToast(weToastType = WeToastType.LOADING, message = stringResource(id = R.string.string_toast_loading))
    }

    val context = LocalContext.current

    val selectMobile = selectMobileLauncher(
        onSuccess = {
            Toast.showText(context, it)
        },
        onFail = {
            Toast.showText(context, "...")
        }
    )

    val fineLocation = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    val coarseLocation = rememberPermissionState(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    val openAlbum = openAlbum {
        Toast.showText(context, "$it")
    }

    val openCamera = openCamera {
        Toast.showText(context, "$it")
    }

    val openContact = openContact {
        Toast.showText(context, "${it.name} : ${it.mobile}")
    }

    var showDragListDialog by rememberSaveable {
        mutableStateOf(false)
    }
    if(showDragListDialog){
        Dialog(onDismissRequest = {
            showDragListDialog = false
        }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
            DragListDemo(
                dragList = uiState.dragList,
                onSwap = viewModel::swapDragList
            )
        }
    }

    ExampleScreenUi(
        helloText = uiState.helloText,
        enableSwitchAppLogo = uiState.enableSwitchAppLogo,
        onSwitchAppLogoClick = {
            viewModel.switchAppLogo(context)
        },
        onFirebaseClick = onFirebaseClick,
        onHttpClick = onHttpClick,
        onGoogleLoginClick = {
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
        onSelectMobileClick = {
            selectMobile()
        },
        onAiChatClick = onAiChatClick,
        onLocationClick = {
            if(fineLocation.status.isGranted || coarseLocation.status.isGranted){
                viewModel.getLocation(context)
            }else{
                if(fineLocation.status.isForeverDenied()){
                    coarseLocation.launchPermissionRequest()
                }else{
                    fineLocation.launchPermissionRequest()
                }
            }
        },
        locationText = uiState.location,
        onOpenAlbumClick = {
            openAlbum()
        },
        onOpenCameraClick = {
            openCamera()
        },
        onOpenContactClick = {
            openContact()
        },
        onDateClick = onDateClick,
        onNestedScrollConnectionScreenClick = onNestedScrollConnectionScreenClick,
        onNestedScrollDispatcherScreenClick = onNestedScrollDispatcherScreenClick,
        onLongClickSortClick = {
            showDragListDialog = true
        },
        onBiometricScreenClick = onBiometricScreenClick,
        onPainterScreenClick = onPainterScreenClick
    )
}

@Composable
fun ExampleScreenUi(
    helloText: String,
    enableSwitchAppLogo: Boolean,
    onSwitchAppLogoClick:()->Unit,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit,
    onSelectMobileClick:()->Unit,
    onAiChatClick:()->Unit,
    locationText: String,
    onLocationClick:()->Unit,
    onOpenAlbumClick:()->Unit,
    onOpenCameraClick:()->Unit,
    onOpenContactClick:()->Unit,
    onDateClick:()->Unit,
    onNestedScrollConnectionScreenClick:()->Unit,
    onNestedScrollDispatcherScreenClick:()->Unit,
    onLongClickSortClick:()->Unit,
    onBiometricScreenClick:()->Unit,
    onPainterScreenClick:()->Unit,
){
    WeScaffold(
        topBar = {
            WeTopNavigationBar(title = stringResource(id = R.string.string_hello_screen_navigation_example))
        }
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                onClick = onSwitchAppLogoClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_get_location),
                summary = locationText,
                onClick = onLocationClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_open_album),
                summary = "",
                onClick = onOpenAlbumClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_open_camera),
                summary = "",
                onClick = onOpenCameraClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_open_contact),
                summary = "",
                onClick = onOpenContactClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_date),
                onClick = onDateClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_scroll_connect),
                onClick = onNestedScrollConnectionScreenClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_scroll_dispatcher),
                onClick = onNestedScrollDispatcherScreenClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_long_click_sort),
                onClick = onLongClickSortClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_biometric),
                onClick = onBiometricScreenClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableClickRow(
                title = stringResource(id = R.string.string_hello_screen_painter),
                onClick = onPainterScreenClick,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
        }
    }
}

@Composable
private fun DragListDemo(
    dragList:List<String>,
    onSwap:(a:Int,b:Int)->Unit
){
    Column {
        DragList(
            modifier = Modifier.background(WeTheme.colorScheme.background).fillMaxSize(),
            list = dragList,
            itemContent = {item, isDrag->
                Row(
                    modifier = Modifier.fillMaxWidth().border(1.dp, WeTheme.colorScheme.outline).height(WeTheme.dimens.navigationBarHeight).background(
                        if(isDrag){
                            WeTheme.colorScheme.primaryButton
                        }else{
                            WeTheme.colorScheme.secondaryButton
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(item)
                }
            },
            onSwap = onSwap
        )
    }
}

@Preview
@Composable
fun PreviewExampleScreen(){
    QuicklyTheme {
        ExampleScreenUi(
            helloText = "",
            onFirebaseClick = {},
            onHttpClick = {},
            onGoogleLoginClick = {},
            onSelectMobileClick = {},
            onAiChatClick = {},
            enableSwitchAppLogo = false,
            onSwitchAppLogoClick = {},
            onLocationClick = {},
            locationText = "",
            onOpenAlbumClick = {},
            onOpenCameraClick = {},
            onOpenContactClick = {},
            onDateClick = {},
            onNestedScrollConnectionScreenClick = {},
            onNestedScrollDispatcherScreenClick = {},
            onLongClickSortClick = {},
            onBiometricScreenClick = {},
            onPainterScreenClick = {}
        )
    }
}
