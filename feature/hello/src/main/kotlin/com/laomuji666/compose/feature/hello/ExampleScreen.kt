package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
    onAiChatClick:()->Unit
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
        }
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
    onOpenContactClick:()->Unit
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
                onClick = onOpenContactClick
            )
        }
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
            onOpenContactClick = {}
        )
    }
}
