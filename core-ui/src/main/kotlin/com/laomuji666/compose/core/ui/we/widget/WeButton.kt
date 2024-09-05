package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme

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
        WeButtonColor.DISABLE -> WeTheme.colorScheme.disableButton
        WeButtonColor.WRONG -> WeTheme.colorScheme.wrongButton
    }
    val textColor = when(weButtonColor){
        WeButtonColor.PRIMARY -> WeTheme.colorScheme.onPrimaryButton
        WeButtonColor.SECONDARY -> WeTheme.colorScheme.onSecondaryButton
        WeButtonColor.DISABLE -> WeTheme.colorScheme.onDisableButton
        WeButtonColor.WRONG -> WeTheme.colorScheme.onWrongButton
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
    DISABLE,
    WRONG
}

@PreviewLightDark
@Composable
fun PreviewWeButton(){
    DefaultWeTheme{
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.PRIMARY,
                text = "主操作按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.SECONDARY,
                text = "弱化按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.DISABLE,
                text = "失效按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.WRONG,
                text = "警告按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}