package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.LocalWeDimens
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.ArrowRight
import com.laomuji666.compose.core.ui.we.icons.WeIcons

@Composable
fun WeTableClickRow(
    title:String,
    summary:String? = null,
    summaryInBottom:Boolean = false,
    showOutLine:Boolean = false,
    onClick:()->Unit = {}
){
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(WeTheme.weColorScheme.rowBackgroundColor)
    ){
        if(summaryInBottom){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalWeDimens.current.paddingHorizontalDp)
                    .height(LocalWeDimens.current.twoRowHeightDp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        style = WeTheme.weTypography.largeText,
                        color = WeTheme.weColorScheme.onRowBackgroundColor
                    )
                    summary?.let {
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = it,
                            style = WeTheme.weTypography.smallText,
                            color = WeTheme.weColorScheme.onRowBackSecondaryColor
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Image(
                    imageVector = WeIcons.ArrowRight,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(WeTheme.weColorScheme.onRowBackSecondaryColor),
                    modifier = Modifier.height(LocalWeDimens.current.iconHeightDp)
                )
            }
        }else{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalWeDimens.current.paddingHorizontalDp)
                    .height(LocalWeDimens.current.rowHeightDp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = WeTheme.weTypography.largeText,
                    color = WeTheme.weColorScheme.onRowBackgroundColor
                )
                Spacer(modifier = Modifier.weight(1f))
                summary?.let {
                    Text(
                        text = it,
                        style = WeTheme.weTypography.mediumText,
                        color = WeTheme.weColorScheme.onRowBackSecondaryColor
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Image(
                    imageVector = WeIcons.ArrowRight,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(WeTheme.weColorScheme.onRowBackSecondaryColor),
                    modifier = Modifier.height(LocalWeDimens.current.iconHeightDp)
                )
            }
        }
        if(showOutLine){
            Spacer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = LocalWeDimens.current.paddingHorizontalDp)
                    .height(1.dp)
                    .background(WeTheme.weColorScheme.outlineColor)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTableClickRow1(){
    QuicklyTheme {
        WeTableClickRow(
            title = "单行标题",
            summary = "详细信息",
            summaryInBottom = true
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTableClickRow2(){
    QuicklyTheme {
        WeTableClickRow(
            title = "单行标题",
            summary = "详细信息"
        )
    }
}