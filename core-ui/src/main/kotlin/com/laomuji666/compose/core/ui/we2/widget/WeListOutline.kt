package com.laomuji666.compose.core.ui.we2.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we2.WeTheme

@Composable
fun WeListOutline(
    modifier: Modifier = Modifier,
    weListOutlineType: WeListOutlineType = WeListOutlineType.FULL,
){
    when(weListOutlineType){
        WeListOutlineType.NONE -> {

        }
        WeListOutlineType.FULL -> {
            Spacer(
                modifier = modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(WeTheme.colorScheme.outline)
            )
        }
        WeListOutlineType.PADDING_HORIZONTAL -> {
            Spacer(
                modifier = modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(horizontal = WeTheme.dimens.listPaddingHorizontal)
                    .background(WeTheme.colorScheme.outline)
            )
        }

        WeListOutlineType.SPLIT -> {
            Spacer(
                modifier = modifier
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(WeTheme.colorScheme.outline)
            )
        }
    }
}

enum class WeListOutlineType{
    NONE,
    FULL,
    PADDING_HORIZONTAL,
    SPLIT
}