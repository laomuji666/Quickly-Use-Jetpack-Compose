package com.laomuji666.compose.core.ui.we2

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
    weDimens: WeDimens = DefaultWeDimens,
    weTypography: WeTypography = DefaultWeTypography,
    content: @Composable () -> Unit,
) {
    val orientation = LocalConfiguration.current.orientation
    val screenOrientation by remember { derivedStateOf { orientation } }
    CompositionLocalProvider(
        LocalDensity provides if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) Density(
            density = LocalContext.current.resources.displayMetrics.widthPixels / 360f,
            fontScale = LocalDensity.current.fontScale
        ) else LocalDensity.current,
        LocalWeColorScheme provides weColorScheme,
        LocalWeDimens provides weDimens,
        LocalWeTypography provides weTypography
    ) {
        content()
    }
}

object WeTheme{
    val colorScheme: WeColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalWeColorScheme.current


    val dimens: WeDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalWeDimens.current

    val typography: WeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalWeTypography.current
}