package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableTitle(
    modifier: Modifier = Modifier,
    title:String
){
    Row(
        modifier = modifier.background(Color.Transparent).fillMaxWidth().padding(horizontal = WeTheme.dimens.listPaddingHorizontal)
    ) {
        Text(
            text = title,
            style = WeTheme.typography.desc,
            color = WeTheme.colorScheme.fontColor50,
            modifier = Modifier.padding(vertical = WeTheme.dimens.listPaddingHorizontal / 2)
        )
    }
}