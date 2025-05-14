package com.laomuji888.compose.core.ui.we.widget.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.icons.ChatsSelect
import com.laomuji888.compose.core.ui.we.icons.ChatsUnselect
import com.laomuji888.compose.core.ui.we.icons.ContactsSelect
import com.laomuji888.compose.core.ui.we.icons.ContactsUnselect
import com.laomuji888.compose.core.ui.we.icons.MeSelect
import com.laomuji888.compose.core.ui.we.icons.MeUnselect
import com.laomuji888.compose.core.ui.we.icons.WeIcons

@PreviewLightDark
@Composable
fun PreviewWeBottomBar() {
    var selected by remember { mutableIntStateOf(0) }
    QuicklyTheme {
        WeBottomBar {
            WeBottomBarItem(
                title = "微信",
                selected = selected == 0,
                onClick = { selected = 0 },
                unSelectImageVector = WeIcons.ChatsUnselect,
                selectImageVector = WeIcons.ChatsSelect
            )
            WeBottomBarItem(
                title = "通讯录",
                selected = selected == 1,
                onClick = { selected = 1 },
                unSelectImageVector = WeIcons.ContactsUnselect,
                selectImageVector = WeIcons.ContactsSelect
            )
            WeBottomBarItem(
                title = "我",
                selected = selected == 3,
                onClick = { selected = 3 },
                unSelectImageVector = WeIcons.MeUnselect,
                selectImageVector = WeIcons.MeSelect
            )
        }
    }
}