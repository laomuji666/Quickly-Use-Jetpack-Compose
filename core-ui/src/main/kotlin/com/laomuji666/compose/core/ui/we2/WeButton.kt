package com.laomuji666.compose.core.ui.we2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun WeButton(
    weButtonType: WeButtonType = WeButtonType.SMALL,
    weButtonColor: WeButtonColor = WeButtonColor.PRIMARY,
    text:String,
    onClick:()->Unit
){
    val buttonWidth = when(weButtonType){
        WeButtonType.BIG -> WeTheme.dimens.bigButtonWidth
        WeButtonType.SMALL -> WeTheme.dimens.smallButtonWidth
    }
    val buttonHeight = when(weButtonType){
        WeButtonType.BIG -> WeTheme.dimens.bigButtonHeight
        WeButtonType.SMALL -> WeTheme.dimens.smallButtonHeight
    }
    val buttonRoundedCornerDp = when(weButtonType){
        WeButtonType.BIG -> WeTheme.dimens.bigButtonRoundedCornerDp
        WeButtonType.SMALL -> WeTheme.dimens.smallButtonRoundedCornerDp
    }
    val buttonColor = when(weButtonColor) {
        WeButtonColor.PRIMARY -> WeTheme.colorScheme.primaryButton
        WeButtonColor.SECONDARY -> WeTheme.colorScheme.secondaryButton
        WeButtonColor.TERTIARY -> WeTheme.colorScheme.tertiaryButton
    }
    val textColor = when(weButtonColor){
        WeButtonColor.PRIMARY -> WeTheme.colorScheme.onPrimaryButton
        WeButtonColor.SECONDARY -> WeTheme.colorScheme.onSecondaryButton
        WeButtonColor.TERTIARY -> WeTheme.colorScheme.onTertiaryButton
    }
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(buttonRoundedCornerDp))
            .defaultMinSize(minWidth = buttonWidth, minHeight = buttonHeight)
            .clickable { onClick() }
            .background(buttonColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = WeTheme.typography.emTitle,
            color = textColor
        )
    }
}

enum class WeButtonType{
    BIG,
    SMALL
}

enum class WeButtonColor{
    PRIMARY,
    SECONDARY,
    TERTIARY
}

@PreviewLightDark
@Composable
fun PreviewWeButton(){
    DefaultWeTheme{
        Column(
            modifier = Modifier.fillMaxWidth().background(WeTheme.colorScheme.backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.PRIMARY,
                text = "完成",
                onClick = {}
            )
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.SECONDARY,
                text = "取消",
                onClick = {}
            )
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.TERTIARY,
                text = "取消",
                onClick = {}
            )
            WeButton(
                weButtonType = WeButtonType.SMALL,
                weButtonColor = WeButtonColor.PRIMARY,
                text = "完成",
                onClick = {}
            )
            WeButton(
                weButtonType = WeButtonType.SMALL,
                weButtonColor = WeButtonColor.SECONDARY,
                text = "取消",
                onClick = {}
            )
            WeButton(
                weButtonType = WeButtonType.SMALL,
                weButtonColor = WeButtonColor.TERTIARY,
                text = "取消",
                onClick = {}
            )
        }
    }
}