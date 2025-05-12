package com.laomuji888.compose.core.ui.we

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.view.WindowInsets
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import com.laomuji888.compose.core.ui.WeIndication
import com.laomuji888.compose.core.ui.ifCondition

@Composable
internal fun DefaultWeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val weColorScheme = if (darkTheme) DarkWeColorScheme else LightWeColorScheme
    WeTheme(
        weColorScheme = weColorScheme
    ) {
        WeBaseContent(content = content)
    }
}

@Composable
private fun WeBaseContent(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val isOldWindowInsetsApi = Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM
    if (!view.isInEditMode) {
        val bottomNavigationBarBackground =
            WeTheme.colorScheme.bottomNavigationBarBackground.toArgb()
        //每次成功重组时,设置底部导航栏颜色
        SideEffect {
            val window = (view.context as Activity).window
            if (isOldWindowInsetsApi) {
                //API35结束的API,因为限定了版本,可以抑制DEPRECATION
                @Suppress("DEPRECATION")
                window.navigationBarColor = bottomNavigationBarBackground
            } else {
                window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                    view.setBackgroundColor(bottomNavigationBarBackground)
                    view.setPadding(
                        0,
                        0,
                        0,
                        insets.getInsets(WindowInsets.Type.navigationBars()).bottom
                    )
                    insets
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .background(WeTheme.colorScheme.background)
            .ifCondition(condition = isOldWindowInsetsApi, onTrue = {
                navigationBarsPadding()
            })
    ) {
        content()
    }
}

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
            density = LocalContext.current.resources.displayMetrics.widthPixels / 375f,
            fontScale = LocalDensity.current.fontScale
        ) else LocalDensity.current,
        LocalIndication provides remember {
            WeIndication(weColorScheme.pressed)
        },
        LocalWeColorScheme provides weColorScheme,
        LocalWeDimens provides weDimens,
        LocalWeTypography provides weTypography
    ) {
        content()
    }
}

object WeTheme {
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