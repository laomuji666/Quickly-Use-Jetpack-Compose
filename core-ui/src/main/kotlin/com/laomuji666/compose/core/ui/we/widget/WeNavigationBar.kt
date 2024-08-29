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
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.ChatsSelect
import com.laomuji666.compose.core.ui.we.icons.ChatsUnselect
import com.laomuji666.compose.core.ui.we.icons.ContactsSelect
import com.laomuji666.compose.core.ui.we.icons.ContactsUnselect
import com.laomuji666.compose.core.ui.we.icons.MeSelect
import com.laomuji666.compose.core.ui.we.icons.MeUnselect

@Composable
fun RowScope.WeNavigationBarItem(
    title: String,
    selected: Boolean,
    unSelectImageVector: ImageVector? = null,
    selectImageVector: ImageVector? = unSelectImageVector,
    onClick: () -> Unit
){
    val color = if(selected)WeTheme.colorScheme.navigationBarSelect else WeTheme.colorScheme.navigationBarUnSelect
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
        if(selected){
            selectImageVector?.let {
                Image(
                    imageVector = it,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(color),
                    modifier = Modifier.height(WeTheme.dimens.navigationBarIconSize)
                )
            }
        }else{
            unSelectImageVector?.let {
                Image(
                    imageVector = it,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(color),
                    modifier = Modifier.height(WeTheme.dimens.navigationBarIconSize)
                )
            }
        }

        Text(
            text = title,
            style = WeTheme.typography.footnote,
            color = color
        )
    }
}

@Composable
fun WeNavigationBar(
    content: @Composable RowScope.() -> Unit
){
    Column {
        WeTableRowOutline(
            color = WeTheme.colorScheme.navigationBarOutline,
            weTableRowOutlineType = WeTableRowOutlineType.FULL
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WeTheme.colorScheme.navigationBarBackground)
                .height(WeTheme.dimens.navigationBarHeight)
        ) {
            content()
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewWeNavigationBar(){
    var selected by remember { mutableIntStateOf(0) }
    QuicklyTheme {
        WeNavigationBar{
            WeNavigationBarItem(
                title = "微信",
                selected = selected == 0,
                onClick = { selected = 0 },
                unSelectImageVector = WeIcons.ChatsUnselect,
                selectImageVector = WeIcons.ChatsSelect
            )
            WeNavigationBarItem(
                title = "通讯录",
                selected = selected == 1,
                onClick = { selected = 1 },
                unSelectImageVector = WeIcons.ContactsUnselect,
                selectImageVector = WeIcons.ContactsSelect
            )
            WeNavigationBarItem(
                title = "发现",
                selected = selected == 2,
                onClick = { selected = 2 },
                unSelectImageVector = WeIcons.ChatsUnselect,
                selectImageVector = WeIcons.ChatsSelect
            )
            WeNavigationBarItem(
                title = "我",
                selected = selected == 3,
                onClick = { selected = 3 },
                unSelectImageVector = WeIcons.MeUnselect,
                selectImageVector = WeIcons.MeSelect
            )
        }
    }
}