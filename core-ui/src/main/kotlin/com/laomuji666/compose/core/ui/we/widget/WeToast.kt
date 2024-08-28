package com.laomuji666.compose.core.ui.we.widget

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.window.Popup
import com.laomuji666.compose.core.ui.R
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Done
import com.laomuji666.compose.core.ui.we.icons.Error
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun WeToast(
    weToastType: WeToastType,
    message: String = stringResource(
        when(weToastType){
            WeToastType.DONE -> com.laomuji666.compose.res.R.string.string_toast_done
            WeToastType.LOADING -> com.laomuji666.compose.res.R.string.string_toast_loading
            WeToastType.ERROR -> com.laomuji666.compose.res.R.string.string_toast_error
        }
    ),
    onDismissRequest: ()->Unit = {}
){
    Popup(onDismissRequest = onDismissRequest) {
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(WeTheme.weDimens.roundedCornerDp))
                    .background(Color(0xFF000000).copy(alpha = 0.18f))
                    .size(WeTheme.weDimens.toastSize),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when(weToastType){
                    WeToastType.DONE -> {
                        Image(
                            imageVector = WeIcons.Done,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.size(WeTheme.weDimens.toastIconSize)
                        )
                    }
                    WeToastType.LOADING -> {
                        BackHandler(
                            enabled = true,
                            onBack = onDismissRequest
                        )
                        var targetDegree by remember { mutableFloatStateOf(0f) }
                        LaunchedEffect(Unit) {
                            while (isActive){
                                targetDegree +=360f
                                if(targetDegree>360000f){
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
                            painter = painterResource(id = R.drawable.loading),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .size(WeTheme.weDimens.toastIconSize)
                                .rotate(rotateDegree)
                        )
                    }
                    WeToastType.ERROR -> {
                        Image(
                            imageVector = WeIcons.Error,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.size(WeTheme.weDimens.toastIconSize)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(WeTheme.weDimens.paddingHorizontalDp))
                Text(
                    text = message,
                    style = WeTheme.weTypography.smallTitle,
                    color = WeTheme.weColorScheme.onPopMenuBackgroundColor
                )
            }
        }
    }
}

enum class WeToastType{
    DONE,
    LOADING,
    ERROR
}

@PreviewLightDark
@Composable
fun PreviewWeToast1(){
    QuicklyTheme {
        WeToast(WeToastType.DONE,"已发送")
    }
}

@PreviewLightDark
@Composable
fun PreviewWeToast2(){
    QuicklyTheme {
        WeToast(WeToastType.LOADING,"加载中")
    }
}

@PreviewLightDark
@Composable
fun PreviewWeToast3(){
    QuicklyTheme {
        WeToast(WeToastType.ERROR,"获取链接失败")
    }
}