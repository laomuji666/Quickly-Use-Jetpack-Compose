package com.laomuji666.compose.feature.youtubedl

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.laomuji666.compose.core.ui.theme.QuicklyTheme

@Composable
fun YoutubeDLScreen(
    viewModel: YoutubeDLScreenViewModel = hiltViewModel()
){

}

@Preview
@Composable
private fun PreviewYoutubeDLScreen() {
    QuicklyTheme {
        YoutubeDLScreen()
    }
}