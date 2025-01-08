package com.laomuji666.compose.feature.demo.device

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.common.Toast
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.view.LoadingDialog
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeTableSwitchRow
import com.laomuji666.compose.launcher.openAlbum
import com.laomuji666.compose.launcher.openCamera
import com.laomuji666.compose.launcher.openContact
import com.laomuji666.compose.launcher.selectMobileLauncher
import com.laomuji666.compose.res.R

@Composable
fun DeviceDemoScreen(
    viewModel: DeviceDemoScreenViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoadingDialog(loading = uiState.isLoading)

    val context = LocalContext.current

    val selectMobile = selectMobileLauncher(
        onSuccess = {
            Toast.showText(context, it)
        },
        onFail = {
            Toast.showText(context, "...")
        }
    )

    val openAlbum = openAlbum {
        Toast.showText(context, "$it")
    }

    val openCamera = openCamera {
        Toast.showText(context, "$it")
    }

    val openContact = openContact {
        Toast.showText(context, "${it.name} : ${it.mobile}")
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionMap ->
        val fineLocationGranted = permissionMap[ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissionMap[ACCESS_COARSE_LOCATION] ?: false
        val activity = context as Activity
        if(!fineLocationGranted && !ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_FINE_LOCATION)){
            if(!coarseLocationGranted && !ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_COARSE_LOCATION)){
                Toast.showText(context = context, resId = R.string.string_permission_location_forever_denied)
            }
        }
    }

    DeviceDemoScreenUi(
        uiState = uiState,
        onSwitchAppLogoClick = {
            viewModel.switchAppLogo(context)
        },
        onLocationClick = {
            if(ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED
            ){
                viewModel.getLocation(context)
            }else{
                launcherMultiplePermissions.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
            }
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
        }
    )
}

@Composable
private fun DeviceDemoScreenUi(
    uiState: DeviceDemoScreenUiState,
    onSwitchAppLogoClick: ()->Unit,
    onLocationClick: ()->Unit,
    onSelectMobileClick: ()->Unit,
    onOpenAlbumClick: ()->Unit,
    onOpenCameraClick: ()->Unit,
    onOpenContactClick: ()->Unit
){
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        WeTableSwitchRow(
            title = stringResource(id = R.string.string_demo_screen_switch_app_logo),
            checked = uiState.enableSwitchAppLogo,
            onClick = onSwitchAppLogoClick,
            weTableRowOutlineType = WeTableRowOutlineType.FULL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_get_location),
            summary = uiState.location,
            onClick = onLocationClick,
            weTableRowOutlineType = WeTableRowOutlineType.FULL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_select_mobile_demo),
            onClick = onSelectMobileClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_open_album),
            onClick = onOpenAlbumClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_open_camera),
            onClick = onOpenCameraClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableClickRow(
            title = stringResource(id = R.string.string_demo_screen_open_contact),
            onClick = onOpenContactClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
    }
}

@Preview
@Composable
private fun PreviewDeviceDemoScreen() {
    QuicklyTheme {
        WeScaffold {
            DeviceDemoScreenUi(
                uiState = DeviceDemoScreenUiState(),
                onSwitchAppLogoClick = {},
                onLocationClick = {},
                onSelectMobileClick = {},
                onOpenAlbumClick = {},
                onOpenCameraClick = {},
                onOpenContactClick = {}
            )
        }
    }
}