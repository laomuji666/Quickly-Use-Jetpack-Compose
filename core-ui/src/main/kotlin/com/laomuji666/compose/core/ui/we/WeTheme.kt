package com.laomuji666.compose.core.ui.we

import android.content.res.Configuration
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
internal fun DefaultWeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val weColorScheme = if(darkTheme) DarkWeColorScheme else LightWeColorScheme
    WeTheme(
        weColorScheme = weColorScheme
    ){
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
            WeIndication()
        },
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

class WeIndication : Indication {
    private class DefaultDebugIndicationInstance(
        private val isPressed: State<Boolean>,
        private val isHovered: State<Boolean>,
        private val isFocused: State<Boolean>,
        private val isDragged: State<Boolean>,
    ) : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
            if(isDragged.value){
                //拖动时不需要指示器效果
                return
            }
            if (isPressed.value) {
                drawRect(color = Color.Black.copy(alpha = 0.3f), size = size)
            } else if (isHovered.value || isFocused.value) {
                drawRect(color = Color.Black.copy(alpha = 0.1f), size = size)
            }
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        val isHovered = interactionSource.collectIsHoveredAsState()
        val isFocused = interactionSource.collectIsFocusedAsState()
        val isDragged = interactionSource.collectIsDraggedAsState()
        return remember(interactionSource) {
            DefaultDebugIndicationInstance(isPressed, isHovered, isFocused, isDragged)
        }
    }
}