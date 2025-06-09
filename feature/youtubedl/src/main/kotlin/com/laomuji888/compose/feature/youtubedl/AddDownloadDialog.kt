package com.laomuji888.compose.feature.youtubedl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.laomuji888.compose.core.ui.we.WeDialog
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.widget.button.WeButton
import com.laomuji888.compose.core.ui.we.widget.button.WeButtonType
import com.laomuji888.compose.core.ui.we.widget.input.WeInput
import com.laomuji888.compose.res.R

@Composable
internal fun AddDownloadDialog(
    onDismissRequest: () -> Unit,
    url: String,
    onValueChange: (String) -> Unit,
    onDownloadVideoClick: () -> Unit
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    WeDialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(WeTheme.colorScheme.rowBackground)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeInput(
                title = stringResource(R.string.string_youtubedl_screen_url_title),
                value = url,
                tip = stringResource(R.string.string_youtubedl_screen_url_tip),
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onImeAction = {
                    softwareKeyboardController?.hide()
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            WeButton(
                text = stringResource(R.string.string_youtubedl_screen_download),
                weButtonType = WeButtonType.Big
            ) {
                softwareKeyboardController?.hide()
                onDownloadVideoClick()
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}