package com.laomuji888.compose.core.ui.we.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.we.icons.WeIcons
import com.laomuji888.compose.core.ui.we.DefaultWeTheme
import com.laomuji888.compose.core.ui.we.WeTheme
import com.laomuji888.compose.core.ui.we.icons.Checked

@Composable
fun WeTableRowRadio(
    title: String,
    checked: Boolean,
    onClick: ()->Unit = {},
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.NONE
){
    WeTableRow(
        start = {
            Text(
                text = title,
                style = WeTheme.typography.title,
                color = WeTheme.colorScheme.fontColorDark
            )
        },
        end = {
            Row(modifier = Modifier.size(WeTheme.dimens.actionIconSize)) {
                AnimatedVisibility(
                    visible = checked,
                    enter = expandHorizontally(
                        animationSpec = tween(800),
                        expandFrom = Alignment.Start
                    ),
                    exit = fadeOut(animationSpec = tween(0))
                ) {
                    Image(
                        imageVector = WeIcons.Checked,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        },
        onClick = onClick,
        weTableRowOutlineType = weTableRowOutlineType
    )
}

@Composable
private fun WeTableRadioColumn(
    modifier: Modifier = Modifier,
    titleList: List<String>,
    currentItem: Int,
    onItemClick: (Int)->Unit
){
    Column(modifier = modifier) {
        titleList.forEachIndexed { index, title ->
            WeTableRowRadio(
                title = title,
                checked = index == currentItem,
                weTableRowOutlineType = if(index == titleList.lastIndex) WeTableRowOutlineType.NONE else WeTableRowOutlineType.PADDING_HORIZONTAL,
                onClick = { onItemClick(index) }
            )
        }
    }
}


@PreviewLightDark
@Composable
fun PreviewWeTableRadioColumn(){
    var currentItem by remember { mutableIntStateOf(0) }
    DefaultWeTheme {
        WeTableRadioColumn(
            titleList = listOf("item1", "item2", "item3", "item4"),
            currentItem = currentItem,
            onItemClick = { currentItem = it }
        )
    }
}
