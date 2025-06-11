package com.laomuji888.compose.feature.main.feature

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.laomuji888.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji888.compose.core.logic.common.Toast
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.view.LoadingDialog
import com.laomuji888.compose.core.ui.we.widget.click.WeClick
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutlineType
import com.laomuji888.compose.core.ui.we.widget.scaffold.WeScaffold
import com.laomuji888.compose.core.ui.we.widget.switc.WeSwitch
import com.laomuji888.compose.feature.main.MainScreenAction
import com.laomuji888.compose.launcher.PermissionUtil
import com.laomuji888.compose.launcher.openAlbum
import com.laomuji888.compose.launcher.openCamera
import com.laomuji888.compose.launcher.openContact
import com.laomuji888.compose.launcher.selectMobileLauncher
import com.laomuji888.compose.res.R

@Composable
fun FeatureScreen(
    viewModel: FeatureScreenViewModel = hiltViewModel(),
    onAction: (MainScreenAction) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoadingDialog(loading = uiState.isLoading)

    val context = LocalContext.current

    val selectMobile = selectMobileLauncher(onSuccess = {
        Toast.showText(context, it)
    }, onFail = {
        Toast.showText(context, "...")
    })

    val openAlbum = openAlbum {
        Toast.showText(context, "$it")
    }

    val openCamera = openCamera {
        Toast.showText(context, "$it")
    }

    val openContact = openContact {
        Toast.showText(context, "${it.name} : ${it.mobile}")
    }

    val launcherMultiplePermissions = PermissionUtil.getPermissionsLauncher(
        permissions = listOf(
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION
        ), onCallback = { _, _, foreverDenied ->
            if (foreverDenied.contains(ACCESS_FINE_LOCATION) && foreverDenied.contains(
                    ACCESS_COARSE_LOCATION
                )
            ) {
                Toast.showText(
                    context = context, resId = R.string.string_permission_location_forever_denied
                )
                PermissionUtil.Setting.openSetting(context)
            }
        })

    FeatureScreenUi(
        uiState = uiState,
        onSwitchAppLogoClick = {
            viewModel.switchAppLogo(context)
        },
        onLocationClick = {
            viewModel.getLocation(context = context, onPermissionDenied = {
                launcherMultiplePermissions()
            })
        },
        onSelectMobileClick = {
            selectMobile()
        },
        onOpenAlbumClick = {
            openAlbum()
        },
        onOpenCameraClick = {
            openCamera()
        },
        onOpenContactClick = {
            openContact()
        },
        onAction = onAction,
    )
}

@Composable
private fun FeatureScreenUi(
    uiState: FeatureScreenUiState,
    onSwitchAppLogoClick: () -> Unit,
    onLocationClick: () -> Unit,
    onSelectMobileClick: () -> Unit,
    onOpenAlbumClick: () -> Unit,
    onOpenCameraClick: () -> Unit,
    onOpenContactClick: () -> Unit,
    onAction: (MainScreenAction) -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_firebase_demo), onClick = {
                onAction(MainScreenAction.OnFirebaseClick)
            }, weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_http_demo), onClick = {
                onAction(MainScreenAction.OnHttpClick)
            }, weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_ai_chat), onClick = {
                onAction(MainScreenAction.OnAiChatClick)
            }, weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_biometric), onClick = {
                onAction(MainScreenAction.OnBiometricScreenClick)
            }, weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_google_login_demo), onClick = {
                GoogleAuthenticate().requestLogin(
                    activityContext = context,
                    onSuccess = { email, idToken ->
                        Toast.showText(context, "$email $idToken")
                    },
                    onFail = {
                        Toast.showText(context, "...")
                    })
            }, weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_web_view_demo), onClick = {
                onAction(MainScreenAction.OnWebViewClick)
            }, weOutlineType = WeOutlineType.Split
        )

        WeSwitch(
            title = stringResource(id = R.string.string_demo_screen_switch_app_logo),
            checked = uiState.enableSwitchAppLogo,
            onCheckedChange = {
                onSwitchAppLogoClick()
            },
            weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_get_location),
            summary = uiState.location,
            onClick = onLocationClick,
            weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_select_mobile_demo),
            onClick = onSelectMobileClick,
            weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_open_album),
            onClick = onOpenAlbumClick,
            weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_open_camera),
            onClick = onOpenCameraClick,
            weOutlineType = WeOutlineType.PaddingHorizontal
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_open_contact),
            onClick = onOpenContactClick,
            weOutlineType = WeOutlineType.PaddingHorizontal
        )
    }
}

@Preview
@Composable
private fun PreviewFeatureScreen() {
    QuicklyTheme {
        WeScaffold {
            FeatureScreenUi(
                uiState = FeatureScreenUiState(),
                onSwitchAppLogoClick = {},
                onLocationClick = {},
                onSelectMobileClick = {},
                onOpenAlbumClick = {},
                onOpenCameraClick = {},
                onOpenContactClick = {},
                onAction = {})
        }
    }
}