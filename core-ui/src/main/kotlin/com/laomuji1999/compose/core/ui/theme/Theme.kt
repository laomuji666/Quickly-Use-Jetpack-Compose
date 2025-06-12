package com.laomuji1999.compose.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji1999.compose.core.ui.we.WeDimensDefault
import com.laomuji1999.compose.core.ui.we.WeTheme
import com.laomuji1999.compose.core.ui.we.WeTypographyDefault
import com.laomuji1999.compose.core.ui.we.colorscheme.WeColorSchemeBlue
import com.laomuji1999.compose.core.ui.we.colorscheme.WeColorSchemeDark
import com.laomuji1999.compose.core.ui.we.colorscheme.WeColorSchemeLight
import com.laomuji1999.compose.core.ui.we.colorscheme.WeThemeColorType

/**
 * 设计系统快速入口,不接受自定义主题.
 * 如果需要自定义,需要使用[WeTheme].
 * @author laomuji666
 * @since 2025/5/23
 */
@Composable
fun QuicklyTheme(
    content: @Composable () -> Unit
) {
    val weThemeColorType by WeThemeColorType.currentWeThemeColorType.collectAsStateWithLifecycle()
    val weColorScheme = when (weThemeColorType) {
        WeThemeColorType.FlowSystem -> if (isSystemInDarkTheme()) WeColorSchemeDark else WeColorSchemeLight
        WeThemeColorType.Light -> WeColorSchemeLight
        WeThemeColorType.Dark -> WeColorSchemeDark
        WeThemeColorType.Blue -> WeColorSchemeBlue
    }
    WeTheme(
        weColorScheme = weColorScheme,
        weDimens = WeDimensDefault,
        weTypography = WeTypographyDefault,
        content = content,
    )
}