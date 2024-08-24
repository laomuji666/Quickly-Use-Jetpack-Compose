package com.laomuji666.compose.core.ui.we

import android.content.res.Configuration
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun WeTheme(
    weColorScheme: WeColorScheme,
    weTypography: WeTypography,
    content: @Composable () -> Unit,
) {
    //竖屏以375来适配屏幕
    val orientation = LocalConfiguration.current.orientation
    val screenOrientation by remember { derivedStateOf { orientation } }
    CompositionLocalProvider(
        LocalWeColorScheme provides weColorScheme,
        LocalWeTypography provides weTypography,
        LocalDensity provides if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) Density(
            density = LocalContext.current.resources.displayMetrics.widthPixels / 375f,
            fontScale = LocalDensity.current.fontScale,
        ) else LocalDensity.current,
        LocalIndication provides remember {
            WeIndication(
                color = weColorScheme.indicationColor,
                blendMode = weColorScheme.indicationBlendMode
            )
        }
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

class WeIndication(
    private val color:Color,
    private val blendMode: BlendMode
) : Indication{
    private class DefaultDebugIndicationInstance(
        private val isPressed: State<Boolean>,
        private val isHovered: State<Boolean>,
        private val isFocused: State<Boolean>,
        private val color:Color,
        private val blendMode: BlendMode
    ) : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
            //BlendMode.DstIn 亮色
            //BlendMode.SrcOver 暗色
            if (isPressed.value || isHovered.value || isFocused.value) {
                drawRect(
                    color = color,
                    size = size,
                    blendMode = blendMode
                )
            }
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        val isHovered = interactionSource.collectIsHoveredAsState()
        val isFocused = interactionSource.collectIsFocusedAsState()
        return remember(interactionSource) {
            DefaultDebugIndicationInstance(isPressed, isHovered, isFocused, color, blendMode)
        }
    }
}