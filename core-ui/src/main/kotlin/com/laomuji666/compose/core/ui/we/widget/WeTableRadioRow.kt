package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Checked
import com.laomuji666.compose.core.ui.we.icons.WeIcons

@Composable
fun WeTableRadioRow(
    title: String,
    checked: Boolean,
    showOutLine:Boolean = false,
    onClick: ()->Unit = {}
){
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .background(WeTheme.weColorScheme.rowBackgroundColor)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = WeTheme.weTypography.largeText,
                color = WeTheme.weColorScheme.onRowBackgroundColor
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.size(26.dp)) {
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
        }
        if(showOutLine){
            Spacer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .height(1.dp)
                    .background(WeTheme.weColorScheme.outlineColor)
            )
        }
    }

}

@Composable
fun WeTableRadioColumn(
    modifier: Modifier = Modifier,
    titleList: List<String>,
    currentItem: Int,
    onItemClick: (Int)->Unit
){
    Column(modifier = modifier) {
        titleList.forEachIndexed { index, title ->
            WeTableRadioRow(
                title = title,
                checked = index == currentItem,
                showOutLine = index != titleList.lastIndex,
                onClick = { onItemClick(index) }
            )
        }
    }
}


@PreviewLightDark
@Composable
fun PreviewWeTableRadioColumn(){
    var currentItem by remember { mutableIntStateOf(0) }
    QuicklyTheme {
        WeTableRadioColumn(
            titleList = listOf("item1", "item2", "item3", "item4"),
            currentItem = currentItem,
            onItemClick = { currentItem = it }
        )
    }
}
