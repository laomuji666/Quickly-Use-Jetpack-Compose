package com.laomuji666.compose.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.laomuji666.compose.core.ui.we.DefaultWeTheme

@Composable
fun QuicklyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    DefaultWeTheme(
        darkTheme = darkTheme,
        content = content
    )
}