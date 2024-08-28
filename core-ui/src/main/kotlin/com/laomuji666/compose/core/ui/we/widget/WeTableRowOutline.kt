package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.WeTheme

enum class WeTableRowOutlineType{
    NONE,
    FULL,
    PADDING_START,
    PADDING_HORIZONTAL,
    SPLIT
}

@Composable
fun WeTableRowOutline(
    weTableRowOutlineType: WeTableRowOutlineType,
    backgroundColor: Color = Color.Transparent
){
    Box(modifier = Modifier.background(backgroundColor).fillMaxWidth()){
        if(weTableRowOutlineType == WeTableRowOutlineType.FULL){
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(WeTheme.weColorScheme.outlineColor)
            )
        }
        if(weTableRowOutlineType == WeTableRowOutlineType.PADDING_START){
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = WeTheme.weDimens.paddingHorizontalDp)
                    .height(1.dp)
                    .background(WeTheme.weColorScheme.outlineColor)
            )
        }
        if(weTableRowOutlineType == WeTableRowOutlineType.PADDING_HORIZONTAL){
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = WeTheme.weDimens.paddingHorizontalDp)
                    .height(1.dp)
                    .background(WeTheme.weColorScheme.outlineColor)
            )
        }
        if(weTableRowOutlineType == WeTableRowOutlineType.SPLIT){
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(WeTheme.weColorScheme.splitLineColor)
            )
        }
    }
}