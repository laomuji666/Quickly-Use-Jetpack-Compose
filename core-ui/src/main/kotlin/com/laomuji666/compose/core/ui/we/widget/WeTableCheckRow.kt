package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.ArrowRight
import com.laomuji666.compose.core.ui.we.icons.Select
import com.laomuji666.compose.core.ui.we.icons.Unselect
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we2.widget.WeListOutlineType
import com.laomuji666.compose.core.ui.we2.widget.WeListRow

@Composable
fun WeTableCheckRow(
    title: String,
    checked: Boolean,
    onClick: ()->Unit = {},
    weListOutlineType: WeListOutlineType = WeListOutlineType.NONE
){
    WeListRow(
        start = {
            Image(
                imageVector = if(checked) WeIcons.Select else WeIcons.Unselect,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(WeTheme.weDimens.iconHeightDp)
            )
            Spacer(modifier = Modifier.width(WeTheme.weDimens.paddingHorizontalDp))
            Text(
                text = title,
                style = WeTheme.weTypography.mediumText,
                color = WeTheme.weColorScheme.onBackgroundColor
            )
        },
        end = {
            Image(
                imageVector = WeIcons.ArrowRight,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(WeTheme.weColorScheme.onRowBackSecondaryColor),
                modifier = Modifier.height(WeTheme.weDimens.iconHeightDp)
            )
        },
        onClick = onClick,
        weListOutlineType = weListOutlineType
    )
}


@PreviewLightDark
@Composable
fun PreviewWeTableCheckRow(){
    var checked1 by remember { mutableStateOf(true) }
    var checked2 by remember { mutableStateOf(false) }
    var checked3 by remember { mutableStateOf(false) }
    var checked4 by remember { mutableStateOf(true) }
    QuicklyTheme {
        Column {
            WeTableCheckRow(
                title = "Item1",
                checked = checked1,
                onClick = { checked1 = !checked1 },
                weListOutlineType = WeListOutlineType.PADDING_HORIZONTAL
            )
            WeTableCheckRow(
                title = "Item2",
                checked = checked2,
                onClick = { checked2 = !checked2 },
                weListOutlineType = WeListOutlineType.PADDING_HORIZONTAL,
            )
            WeTableCheckRow(
                title = "Item3",
                checked = checked3,
                onClick = { checked3 = !checked3 },
                weListOutlineType = WeListOutlineType.PADDING_HORIZONTAL,
            )
            WeTableCheckRow(
                title = "Item4",
                checked = checked4,
                onClick = { checked4 = !checked4 }
            )
        }
    }
}