package com.laomuji666.compose.core.ui

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.invalidateDraw
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                            delay(50)
                            pressCount--
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