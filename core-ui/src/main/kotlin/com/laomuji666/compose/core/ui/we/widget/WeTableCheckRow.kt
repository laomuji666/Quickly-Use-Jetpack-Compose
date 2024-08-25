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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.ArrowRight
import com.laomuji666.compose.core.ui.we.icons.Select
import com.laomuji666.compose.core.ui.we.icons.Unselect
import com.laomuji666.compose.core.ui.we.icons.WeIcons

@Composable
fun WeTableCheckRow(
    title: String,
    checked: Boolean,
    showOutLine:Boolean = false,
    onClick: ()->Unit = {}
){
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(WeTheme.weColorScheme.rowBackgroundColor)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = if(checked) WeIcons.Select else WeIcons.Unselect,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(26.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = WeTheme.weTypography.largeText,
                color = WeTheme.weColorScheme.onRowBackgroundColor
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = WeIcons.ArrowRight,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(WeTheme.weColorScheme.onRowBackSecondaryColor),
                modifier = Modifier.height(26.dp)
            )
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
                showOutLine = true,
                onClick = { checked1 = !checked1 }
            )
            WeTableCheckRow(
                title = "Item2",
                checked = checked2,
                showOutLine = true,
                onClick = { checked2 = !checked2 }
            )
            WeTableCheckRow(
                title = "Item3",
                checked = checked3,
                showOutLine = true,
                onClick = { checked3 = !checked3 }
            )
            WeTableCheckRow(
                title = "Item4",
                checked = checked4,
                onClick = { checked4 = !checked4 }
            )
        }
    }
}