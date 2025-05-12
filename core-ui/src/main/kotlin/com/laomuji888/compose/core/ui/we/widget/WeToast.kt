package com.laomuji888.compose.core.ui.we.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.laomuji888.compose.core.ui.we.DefaultWeTheme
import com.laomuji888.compose.core.ui.we.LocalWeDimens
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.icons.Done
import com.laomuji888.compose.core.ui.we.icons.Error
import com.laomuji888.compose.core.ui.we.icons.Loading
import com.laomuji888.compose.core.ui.we.icons.WeIcons
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun WeToast(
    weToastType: WeToastType,
    message: String,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(12.dp))
                    .background(WeTheme.colorScheme.toastBackgroundColor)
                    .size(LocalWeDimens.current.toastSize),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (weToastType) {
                    WeToastType.DONE -> {
                        Image(
                            imageVector = WeIcons.Done,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.size(LocalWeDimens.current.toastIconSize)
                        )
                    }

                    WeToastType.LOADING -> {
                        var targetDegree by remember { mutableFloatStateOf(0f) }
                        LaunchedEffect(Unit) {
                            while (isActive) {
                                targetDegree += 360f
                                if (targetDegree > 360000f) {
                                    targetDegree = 0f
                                }
                                delay(800)
                            }
                        }
                        val rotateDegree by animateFloatAsState(
                            targetValue = targetDegree,
                            animationSpec = tween(durationMillis = 1000), label = ""
                        )
                        Image(
                            imageVector = WeIcons.Loading,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .size(LocalWeDimens.current.toastIconSize)
                                .rotate(rotateDegree)
                        )
                    }

                    WeToastType.ERROR -> {
                        Image(
                            imageVector = WeIcons.Error,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.size(LocalWeDimens.current.toastIconSize)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(WeTheme.dimens.toastDividerHeight))
                Text(
                    modifier = Modifier.padding(horizontal = WeTheme.dimens.tableRowPaddingHorizontal),
                    text = message,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = WeTheme.typography.title,
                    color = WeTheme.colorScheme.onToastBackgroundColor
                )
            }
        }
    }
}

enum class WeToastType {
    DONE,
    LOADING,
    ERROR
}

@Preview
@Composable
fun PreviewWeToast1() {
    DefaultWeTheme {
        WeToast(WeToastType.DONE, "已发送")
    }
}

@Preview
@Composable
fun PreviewWeToast2() {
    DefaultWeTheme {
        WeToast(WeToastType.LOADING, "加载中")
    }
}

@Preview
@Composable
fun PreviewWeToast3() {
    DefaultWeTheme {
        WeToast(WeToastType.ERROR, "获取链接失败")
    }
}