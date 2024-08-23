package com.laomuji666.compose.core.ui.we

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun WeTheme(
    weColorScheme: WeColorScheme,
    weTypography: WeTypography,
    content: @Composable () -> Unit
) {
    //竖屏以375来适配屏幕
    val orientation = LocalConfiguration.current.orientation
    val screenOrientation by remember { derivedStateOf { orientation } }
    CompositionLocalProvider(
        LocalWeColorScheme provides weColorScheme,
        LocalWeTypography provides weTypography,
        LocalDensity provides if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) Density(
            density = LocalContext.current.resources.displayMetrics.widthPixels / 375f,
            fontScale = LocalContext.current.resources.displayMetrics.widthPixels / 375f,
        ) else LocalDensity.current
    ) {
        content()
    }
}

object WeTheme{
    val weColorScheme: WeColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalWeColorScheme.current

    val weTypography: WeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalWeTypography.current
}