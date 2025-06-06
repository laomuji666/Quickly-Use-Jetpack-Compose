package com.laomuji888.compose.core.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.widget.toast.WeToast
import com.laomuji888.compose.core.ui.we.widget.toast.WeToastType
import com.laomuji888.compose.res.R

@Composable
fun LoadingDialog(loading: Boolean) {
    if (!loading) {
        return
    }
    Dialog(onDismissRequest = {}, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        WeToast(
            weToastType = WeToastType.Loading,
            message = stringResource(R.string.string_toast_loading)
        )
    }

}

@Preview
@Composable
fun PreviewLoadingDialog() {
    QuicklyTheme {
        LoadingDialog(loading = true)
    }
}