package com.laomuji666.compose.feature.biometric

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.authenticate.biometric.BiometricAuthenticate
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableInput
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.res.R.string

@Composable
fun BiometricScreen(
    viewModel: BiometricScreenViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    BiometricScreenUi(
        uiState = uiState,
        onHandleClick = {
            viewModel.handleBiometric(context)
        },
        onTitleChange = viewModel::setTitle,
        onDescriptionChange = viewModel::setDescription
    )
}

@Composable
private fun BiometricScreenUi(
    uiState: BiometricScreenUiState,
    onHandleClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
){
    val text = when(uiState.biometricResult){
        BiometricAuthenticate.BiometricAuthenticateResult.HardwareNotFound -> stringResource(id = string.string_biometric_screen_status_hardware_not_found)
        BiometricAuthenticate.BiometricAuthenticateResult.HardwareUnavailable -> stringResource(id = string.string_biometric_screen_status_hardware_unavailable)
        BiometricAuthenticate.BiometricAuthenticateResult.NotSetBiometric -> stringResource(id = string.string_biometric_screen_status_hardware_not_set)
        is BiometricAuthenticate.BiometricAuthenticateResult.OtherError -> stringResource(id = string.string_biometric_screen_status_hardware_other_error, uiState.biometricResult.msg)
        BiometricAuthenticate.BiometricAuthenticateResult.Success -> stringResource(id = string.string_biometric_screen_status_hardware_success)
        BiometricAuthenticate.BiometricAuthenticateResult.Fail -> stringResource(id = string.string_biometric_screen_status_hardware_fail)
        null -> { "" }
    }
    WeScaffold(
        topBar = {
            WeTopNavigationBar(title = text)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))
            WeTableInput(
                title = stringResource(id = string.string_biometric_screen_title_title),
                tip = stringResource(id = string.string_biometric_screen_title_tip),
                value = uiState.title,
                onValueChange = onTitleChange
            )
            Spacer(modifier = Modifier.height(10.dp))
            WeTableInput(
                title = stringResource(id = string.string_biometric_screen_description_title),
                tip = stringResource(id = string.string_biometric_screen_description_tip),
                value = uiState.description,
                onValueChange = onDescriptionChange
            )
            Spacer(modifier = Modifier.height(20.dp))

            WeButton(
                text = stringResource(id = string.string_biometric_screen_button_title),
                onClick = onHandleClick,
                weButtonType = WeButtonType.BIG
            )
        }

    }
}

@Preview
@Composable
private fun PreviewBiometricScreen() {
    QuicklyTheme {
        BiometricScreenUi(
            uiState = BiometricScreenUiState(),
            onHandleClick = {},
            onTitleChange = {},
            onDescriptionChange = {}
        )
    }
}