package com.laomuji666.compose.core.ui

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun isPreview() = LocalInspectionMode.current

@Composable
fun Modifier.ifCondition(
    condition: Boolean,
    onTrue: @Composable Modifier.() -> Modifier,
    onFalse: @Composable Modifier.() -> Modifier = { this }
): Modifier {
    return if (condition) {
        this.onTrue()
    } else {
        this.onFalse()
    }
}

@Composable
fun Modifier.clickableDebounce(
    timeout: Long = 200L,
    indication: Indication? = LocalIndication.current,
    onClick: () -> Unit
): Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    val currentTime by rememberUpdatedState(newValue = System.currentTimeMillis())
    return this.clickable(
        enabled = currentTime - lastClickTime > timeout,
        indication = indication,
        interactionSource = remember { MutableInteractionSource() },
        onClick = {
            lastClickTime = currentTime
            onClick()
        })
}