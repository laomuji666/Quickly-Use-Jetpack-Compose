package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.LocalWeDimens
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeButton(
    weButtonType: WeButtonType,
    weButtonSizeType: WeButtonSizeType,
    text: String,
    onClick: ()->Unit
){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(LocalWeDimens.current.buttonRoundedCornerDp))
            .background(
                if (weButtonType == WeButtonType.PRIMARY) {
                    WeTheme.weColorScheme.primary
                } else {
                    WeTheme.weColorScheme.secondary
                }
            )
            .width(
                when (weButtonSizeType) {
                    WeButtonSizeType.SMALL -> LocalWeDimens.current.smallButtonWidthDp
                    WeButtonSizeType.BIG -> LocalWeDimens.current.bigButtonWidthDp
                }
            )
            .height(
                when (weButtonSizeType) {
                    WeButtonSizeType.SMALL -> LocalWeDimens.current.smallButtonHeightDp
                    WeButtonSizeType.BIG -> LocalWeDimens.current.bigButtonHeightDp
                }
            )
            .clickable { onClick() }
        ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = when(weButtonSizeType){
                WeButtonSizeType.SMALL -> WeTheme.weTypography.smallText
                WeButtonSizeType.BIG -> WeTheme.weTypography.mediumText
            },
            color = when(weButtonType){
                WeButtonType.PRIMARY -> WeTheme.weColorScheme.secondary
                WeButtonType.SECONDARY -> WeTheme.weColorScheme.primary
                WeButtonType.DISABLE -> WeTheme.weColorScheme.tertiary
                WeButtonType.WRONG -> WeTheme.weColorScheme.error
            }
        )
    }
}

enum class WeButtonType{
    PRIMARY,
    SECONDARY,
    DISABLE,
    WRONG
}

enum class WeButtonSizeType{
    SMALL,
    BIG
}

@PreviewLightDark
@Composable
fun PreviewWeButton1(){
    QuicklyTheme {
        Row(modifier = Modifier.background(WeTheme.weColorScheme.backgroundColor)) {
            Column(modifier = Modifier.padding(20.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                WeButton(
                    weButtonType = WeButtonType.PRIMARY,
                    weButtonSizeType = WeButtonSizeType.BIG,
                    text = "强调按钮",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(20.dp))
                WeButton(
                    weButtonType = WeButtonType.SECONDARY,
                    weButtonSizeType = WeButtonSizeType.BIG,
                    text = "强调按钮",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(20.dp))
                WeButton(
                    weButtonType = WeButtonType.DISABLE,
                    weButtonSizeType = WeButtonSizeType.BIG,
                    text = "强调按钮",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(20.dp))
                WeButton(
                    weButtonType = WeButtonType.WRONG,
                    weButtonSizeType = WeButtonSizeType.BIG,
                    text = "强调按钮",
                    onClick = {}
                )
            }

            Column(modifier = Modifier.padding(20.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                WeButton(
                    weButtonType = WeButtonType.PRIMARY,
                    weButtonSizeType = WeButtonSizeType.SMALL,
                    text = "完成",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(20.dp))
                WeButton(
                    weButtonType = WeButtonType.SECONDARY,
                    weButtonSizeType = WeButtonSizeType.SMALL,
                    text = "完成",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(20.dp))
                WeButton(
                    weButtonType = WeButtonType.DISABLE,
                    weButtonSizeType = WeButtonSizeType.SMALL,
                    text = "失效",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(20.dp))
                WeButton(
                    weButtonType = WeButtonType.WRONG,
                    weButtonSizeType = WeButtonSizeType.SMALL,
                    text = "警示",
                    onClick = {}
                )
            }
        }

    }
}