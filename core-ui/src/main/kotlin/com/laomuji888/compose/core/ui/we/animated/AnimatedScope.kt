package com.laomuji888.compose.core.ui.we.animated

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

interface AnimatedScope{
    var animTime:Int
    var isInOrOut: MutableState<Boolean?>
    @Composable
    fun Show()
    fun hide(callback:()->Unit)
}

class AnimatedScopeImpl: AnimatedScope {
    override var animTime: Int = 400
    override var isInOrOut: MutableState<Boolean?> = mutableStateOf(null)
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    @Composable
    override fun Show() {
        if(isInOrOut.value==null){
            LaunchedEffect(Unit){
                delay(100)
                isInOrOut.value = true
            }
        }
    }

    override fun hide(callback:()->Unit) {
        coroutineScope.launch {
            isInOrOut.value = false
            delay(animTime.toLong())
            callback()
        }
    }
}

@Composable
fun AnimatedSlide(scope:@Composable AnimatedScope.()->Unit, content:@Composable AnimatedScope.()->Unit){
    if(LocalView.current.isInEditMode){
        content(AnimatedScopeImpl())
        return
    }
    val animatedScopeImpl = remember { AnimatedScopeImpl() }
    animatedScopeImpl.scope()
    AnimatedVisibility(
        visible = animatedScopeImpl.isInOrOut.value==true,
        enter = slideIn(
            animationSpec = tween(animatedScopeImpl.animTime),
            initialOffset = {
                IntOffset(
                    x = 0,
                    y = it.height
                )
            }
        ),
        exit = slideOut(
            animationSpec = tween(animatedScopeImpl.animTime),
            targetOffset = {
                IntOffset(
                    x = 0,
                    y = it.height
                )
            }
        )
    ){
        animatedScopeImpl.content()
    }
}