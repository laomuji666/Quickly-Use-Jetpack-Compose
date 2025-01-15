package com.laomuji666.compose.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun isPreview() = LocalInspectionMode.current

@Composable
fun Modifier.ifCondition(condition: Boolean, onTrue:@Composable Modifier.() -> Modifier, onFalse:@Composable Modifier.() -> Modifier = {this}): Modifier{
    return if(condition){
        this.onTrue()
    }else{
        this.onFalse()
    }
}
