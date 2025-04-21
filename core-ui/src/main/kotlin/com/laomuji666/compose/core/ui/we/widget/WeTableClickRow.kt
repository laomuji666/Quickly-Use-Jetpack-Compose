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
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Arrow

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
                        style = WeTheme.typography.title,
                        color = WeTheme.colorScheme.fontColor90
                    )
                    summary?.let {
                        Text(
                            text = it,
                            style = WeTheme.typography.desc,
                            color = WeTheme.colorScheme.fontColor50
                        )
                    }
                }
            }else{
                Text(
                    text = title,
                    style = WeTheme.typography.title,
                    color = WeTheme.colorScheme.fontColor90
                )
            }
        },
        end = {
            if(!summaryInBottom){
                summary?.let {
                    Text(
                        text = it,
                        style = WeTheme.typography.title,
                        color = WeTheme.colorScheme.fontColor50
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Image(
                imageVector = WeIcons.Arrow,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(WeTheme.colorScheme.fontColor50),
                modifier = Modifier.height(WeTheme.dimens.tableIconSize)
            )
        },
        weTableRowType = if(summaryInBottom) WeTableRowType.DOUBLE else WeTableRowType.SINGLE,
        onClick = onClick,
        weTableRowOutlineType = weTableRowOutlineType
    )
}

@PreviewLightDark
@Composable
fun PreviewWeTableClickRow1(){
    DefaultWeTheme {
        WeTableClickRow(
            title = "双行标题",
            summary = "详细信息",
            summaryInBottom = true
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTableClickRow2(){
    DefaultWeTheme {
        WeTableClickRow(
            title = "单行标题",
            summary = "详细信息"
        )
    }
}