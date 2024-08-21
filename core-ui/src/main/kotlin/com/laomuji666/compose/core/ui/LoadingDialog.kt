package com.laomuji666.compose.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingDialog(loading: Boolean) {
    if (!loading) {
        return
    }
    Dialog(onDismissRequest = {}) {
        CircularProgressIndicator(
            strokeWidth = 6.dp,
            modifier = Modifier.size(60.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
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