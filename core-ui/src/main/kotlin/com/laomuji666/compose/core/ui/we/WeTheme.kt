package com.laomuji666.compose.core.ui.we

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.view.WindowInsets
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun DefaultWeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val weColorScheme = if(darkTheme) DarkWeColorScheme else LightWeColorScheme
    WeTheme(
        weColorScheme = weColorScheme
    ){
        SetSystemBarsColor(content = content)
    }
}

@Composable
private fun SetSystemBarsColor(
    content: @Composable () -> Unit
){
    val view = LocalView.current
    val isOldWindowInsetsApi = Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM
    if(!view.isInEditMode){
        val bottomNavigationBarBackground = WeTheme.colorScheme.bottomNavigationBarBackground.toArgb()
        //每次成功重组时,设置底部导航栏颜色
        SideEffect {
            val window = (view.context as Activity).window
            if (isOldWindowInsetsApi) {
                //API35结束的API,因为限定了版本,可以抑制DEPRECATION
                @Suppress("DEPRECATION")
                window.navigationBarColor = bottomNavigationBarBackground
            }else{
                window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                    view.setBackgroundColor(bottomNavigationBarBackground)
                    view.setPadding(0, 0, 0, insets.getInsets(WindowInsets.Type.navigationBars()).bottom)
                    insets
                }
            }
        }
    }
    Box(modifier = Modifier.then(
        if (isOldWindowInsetsApi){
            Modifier
                .background(WeTheme.colorScheme.bottomNavigationBarBackground)
                .navigationBarsPadding()
        }else{
            Modifier
        }
    )) {
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

data class WeIndication(
    val pressedColor: Color,
    val focusedColor: Color = pressedColor,
    val dragColor: Color = pressedColor,
) : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode =
        WeDefaultIndicationInstance(interactionSource)

    override fun hashCode(): Int {
        var result = pressedColor.hashCode()
        result = 31 * result + focusedColor.hashCode()
        result = 31 * result + dragColor.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WeIndication) return false
        if (pressedColor != other.pressedColor) return false
        if (focusedColor != other.focusedColor) return false
        if (dragColor != other.dragColor) return false
        return true
    }

    private inner class WeDefaultIndicationInstance(
        private val interactionSource: InteractionSource
    ) : Modifier.Node(), DrawModifierNode {
        private var isPressed = false
        private var isHovered = false
        private var isFocused = false
        private var isDrag = false

        override fun onAttach() {
            coroutineScope.launch {
                var pressCount = 0
                var hoverCount = 0
                var focusCount = 0
                var dragCount = 0
                interactionSource.interactions.collect { interaction ->
                    when (interaction) {
                        is PressInteraction.Press -> {
                            pressCount++
                        }
                        is PressInteraction.Release -> {
                            coroutineScope.launch {
                                delay(50)
                                pressCount--
                            }
                        }
                        is PressInteraction.Cancel -> pressCount--

                        is HoverInteraction.Enter -> hoverCount++
                        is HoverInteraction.Exit -> hoverCount--

                        is FocusInteraction.Focus -> focusCount++
                        is FocusInteraction.Unfocus -> focusCount--

                        is DragInteraction.Start -> dragCount++
                        is DragInteraction.Stop -> dragCount--
                        is DragInteraction.Cancel -> dragCount--
                    }
                    val pressed = pressCount > 0
                    val hovered = hoverCount > 0
                    val focused = focusCount > 0
                    val dragged = dragCount > 0
                    var invalidateNeeded = false
                    if (isPressed != pressed) {
                        isPressed = pressed
                        invalidateNeeded = true
                    }
                    if (isHovered != hovered) {
                        isHovered = hovered
                        invalidateNeeded = true
                    }
                    if (isFocused != focused) {
                        isFocused = focused
                        invalidateNeeded = true
                    }
                    if (isDrag != dragged) {
                        isDrag = dragged
                        invalidateNeeded = true
                    }
                    if (invalidateNeeded) invalidateDraw()
                }
            }
        }

        override fun ContentDrawScope.draw() {
            drawContent()
            if (isPressed) {
                drawRect(color = pressedColor, size = size)
            } else if (isHovered || isFocused) {
                drawRect(color = focusedColor, size = size)
            } else if (isDrag) {
                drawRect(color = dragColor, size = size)
            }
        }
    }
}