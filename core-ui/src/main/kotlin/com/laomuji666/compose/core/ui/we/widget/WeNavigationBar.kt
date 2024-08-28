package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Example
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.icons.Widget

@Composable
fun RowScope.WeNavigationBarItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    imageVector: ImageVector? = null,
){
    val color = if(selected) WeTheme.weColorScheme.primary else WeTheme.weColorScheme.onBackgroundColor
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        imageVector?.let {
            Image(
                imageVector = it,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier.height(WeTheme.weDimens.iconHeightDp)
            )
        }
        Text(
            text = title,
            style = WeTheme.weTypography.smallText,
            color = color
        )
    }
}

@Composable
fun WeNavigationBar(
    content: @Composable RowScope.() -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(WeTheme.weColorScheme.rowBackgroundColor)
            .height(WeTheme.weDimens.rowHeightDp)
    ) {
        content()
    }
}

@PreviewLightDark
@Composable
fun PreviewWeNavigationBar(){
    var selected by remember { mutableIntStateOf(0) }
    QuicklyTheme {
        WeNavigationBar{
            WeNavigationBarItem(
                imageVector = WeIcons.Example,
                title = "功能示例",
                selected = selected == 0,
                onClick = { selected = 0 }
            )
            WeNavigationBarItem(
                imageVector = WeIcons.Widget,
                title = "UI组件",
                selected = selected == 1,
                onClick = { selected = 1 }
            )
        }
    }
}