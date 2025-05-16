package com.laomuji888.compose.core.ui.we.widget.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.laomuji888.compose.core.ui.theme.QuicklyTheme

@PreviewLightDark
@Composable
fun PreviewWeButton() {
    QuicklyTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.Big,
                weButtonColor = WeButtonColor.Primary,
                text = "主操作按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.Big,
                weButtonColor = WeButtonColor.Secondary,
                text = "弱化按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.Big,
                weButtonColor = WeButtonColor.Disable,
                text = "失效按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            WeButton(
                weButtonType = WeButtonType.Big,
                weButtonColor = WeButtonColor.Wrong,
                text = "警告按钮",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}