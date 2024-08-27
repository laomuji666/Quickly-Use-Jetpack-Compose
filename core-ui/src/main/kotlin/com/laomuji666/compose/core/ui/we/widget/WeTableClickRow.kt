package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onClick:()->Unit = {},
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.NONE
){
    WeTableRow(
        start = {
            if(summaryInBottom){
                Column {
                    Text(
                        text = title,
                        style = WeTheme.weTypography.mediumText,
                        color = WeTheme.weColorScheme.onBackgroundColor
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
            }else{
                Text(
                    text = title,
                    style = WeTheme.weTypography.mediumText,
                    color = WeTheme.weColorScheme.onBackgroundColor
                )
            }
        },
        end = {
            if(!summaryInBottom){
                summary?.let {
                    Text(
                        text = it,
                        style = WeTheme.weTypography.mediumText,
                        color = WeTheme.weColorScheme.onRowBackSecondaryColor
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Image(
                imageVector = WeIcons.ArrowRight,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(WeTheme.weColorScheme.onRowBackSecondaryColor),
                modifier = Modifier.height(LocalWeDimens.current.iconHeightDp)
            )
        },
        rowHeight = if(summaryInBottom) LocalWeDimens.current.twoRowHeightDp else LocalWeDimens.current.rowHeightDp,
        onClick = onClick,
        weTableRowOutlineType = weTableRowOutlineType
    )
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