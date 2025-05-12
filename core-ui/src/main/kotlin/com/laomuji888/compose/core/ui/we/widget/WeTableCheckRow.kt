package com.laomuji888.compose.core.ui.we.widget

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.we.icons.WeIcons
import com.laomuji888.compose.core.ui.we.DefaultWeTheme
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.icons.Select
import com.laomuji888.compose.core.ui.we.icons.Unselect

@Composable
fun WeTableCheckRow(
    title: String,
    checked: Boolean,
    onClick: ()->Unit = {},
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.NONE
){
    WeTableRow(
        start = {
            Image(
                imageVector = if(checked) WeIcons.Select else WeIcons.Unselect,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(WeTheme.dimens.actionIconSize)
            )
            Spacer(modifier = Modifier.width(WeTheme.dimens.tableRowPaddingHorizontal))
            Text(
                text = title,
                style = WeTheme.typography.title,
                color = WeTheme.colorScheme.fontColorDark
            )
        },
        onClick = onClick,
        weTableRowOutlineType = weTableRowOutlineType
    )
}


@PreviewLightDark
@Composable
fun PreviewWeTableCheckRow(){
    var checked1 by remember { mutableStateOf(true) }
    var checked2 by remember { mutableStateOf(false) }
    var checked3 by remember { mutableStateOf(false) }
    var checked4 by remember { mutableStateOf(true) }
    DefaultWeTheme {
        Column {
            WeTableCheckRow(
                title = "Item1",
                checked = checked1,
                onClick = { checked1 = !checked1 },
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
            )
            WeTableCheckRow(
                title = "Item2",
                checked = checked2,
                onClick = { checked2 = !checked2 },
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
            )
            WeTableCheckRow(
                title = "Item3",
                checked = checked3,
                onClick = { checked3 = !checked3 },
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
            )
            WeTableCheckRow(
                title = "Item4",
                checked = checked4,
                onClick = { checked4 = !checked4 }
            )
        }
    }
}