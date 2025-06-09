package com.laomuji888.compose.core.ui.we.widget.actionsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.DialogProperties
import com.laomuji888.compose.core.ui.clickableDebounce
import com.laomuji888.compose.core.ui.we.WeDialog
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.animated.AnimatedSlideFromBottom
import com.laomuji888.compose.core.ui.we.animated.AnimatedSlideFromBottomScope


@Composable
fun WeActionSheetDialog(
    onDismissRequest: () -> Unit = {}, content: @Composable AnimatedSlideFromBottomScope.() -> Unit
) {
    WeDialog(
        onDismissRequest = {}, properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedSlideFromBottom {
                Box(
                    modifier = Modifier
                        .clickableDebounce(onClick = {
                            hide { onDismissRequest() }
                        })
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .clip(
                                RoundedCornerShape(
                                    topStart = WeTheme.dimens.actionSheetRoundedCornerDp,
                                    topEnd = WeTheme.dimens.actionSheetRoundedCornerDp
                                )
                            )
                    ) {
                        content()
                    }
                }
            }
        }
    }
}