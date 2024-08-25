package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableSwitchRow(
    title: String,
    checked: Boolean,
    showOutLine:Boolean = false,
    onClick: ()->Unit = {}
){
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .background(WeTheme.weColorScheme.rowBackgroundColor)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = WeTheme.weTypography.largeText,
                color = WeTheme.weColorScheme.onRowBackgroundColor
            )
            Spacer(modifier = Modifier.weight(1f))
            WeSwitch(
                checked = checked,
                onClick = onClick
            )
        }
        if(showOutLine){
            Spacer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .height(1.dp)
                    .background(WeTheme.weColorScheme.outlineColor)
            )
        }
    }

}

@Composable
fun WeSwitch(
    checked: Boolean,
    onClick: () -> Unit,
    width: Dp = 51.dp,
    height: Dp = 31.dp,
    thumbSize: Dp = 27.dp,
    gap: Dp = 2.dp
) {
    val animationDuration = 300
    val offsetX by animateDpAsState(
        targetValue = if (checked) width - thumbSize - gap else gap,
        animationSpec = tween(durationMillis = animationDuration), label = ""
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(if (checked) Color(0xFF07C160) else Color(0xFFE5E5E5))
    ) {
        Spacer(modifier = Modifier
            .size(thumbSize)
            .offset(x = offsetX)
            .shadow(elevation = 3.dp, shape = CircleShape)
            .background(Color.White, shape = CircleShape)
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTableSwitchRow(){
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(true) }
    QuicklyTheme {
        Column {
            WeTableSwitchRow(
                title = "勿扰模式",
                checked = checked1,
                showOutLine = true,
                onClick = { checked1 = !checked1 }
            )
            WeTableSwitchRow(
                title = "消息推送",
                checked = checked2,
                onClick = { checked2 = !checked2 }
            )
        }
    }
}