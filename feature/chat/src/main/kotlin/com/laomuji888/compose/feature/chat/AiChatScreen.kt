package com.laomuji888.compose.feature.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.logic.database.entity.ContactInfoEntity
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.icons.Add
import com.laomuji888.compose.core.ui.we.icons.ContactsSelect
import com.laomuji888.compose.core.ui.we.icons.ContactsUnselect
import com.laomuji888.compose.core.ui.we.icons.MeSelect
import com.laomuji888.compose.core.ui.we.icons.MeUnselect
import com.laomuji888.compose.core.ui.we.icons.Search
import com.laomuji888.compose.core.ui.we.icons.WeIcons
import com.laomuji888.compose.core.ui.we.widget.WeBottomNavigationBar
import com.laomuji888.compose.core.ui.we.widget.WeBottomNavigationBarItem
import com.laomuji888.compose.core.ui.we.widget.WeScaffold
import com.laomuji888.compose.core.ui.we.widget.WeTopActionBar
import com.laomuji888.compose.core.ui.we.widget.WeTopNavigationBarAction
import com.laomuji888.compose.core.ui.we.widget.WeTopNavigationBarSpace
import com.laomuji888.compose.feature.chat.contacts.ContactsScreen
import com.laomuji888.compose.feature.chat.me.MeScreen
import com.laomuji888.compose.res.R
import kotlinx.coroutines.launch

@Composable
fun AiChatScreen(
    onContactClick: (ContactInfoEntity)->Unit
){
    AiChatScreenUi(
        contactsContent = {
            ContactsScreen(
                onContactClick = onContactClick
            )
        },
        meContent = {
            MeScreen()
        }
    )
}

@Composable
private fun AiChatScreenUi(
    contactsContent:@Composable ()->Unit,
    meContent:@Composable ()->Unit
){
    val pagerState = rememberPagerState(
        initialPage = AiScreenSelectEnum.CONTACTS.ordinal,
        pageCount = { AiScreenSelectEnum.entries.size }
    )
    WeScaffold(
        bottomBar = {
            BottomBar(pagerState)
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = 3
        ) {
            when (it) {
                AiScreenSelectEnum.CONTACTS.ordinal -> contactsContent()
                AiScreenSelectEnum.ME.ordinal -> meContent()
            }
        }
    }
}

@Composable
fun AiChatTopBar(
    title:String,
    onMenuClick:()->Unit
){
    WeTopActionBar(
        title = title,
        actions = {
            WeTopNavigationBarAction(
                imageVector = WeIcons.Search
            )
            WeTopNavigationBarSpace()
            WeTopNavigationBarAction(
                imageVector = WeIcons.Add,
                onActionClick = onMenuClick
            )
        }
    )
}

@Composable
private fun BottomBar(
    pagerState: PagerState
){
    val coroutineScope = rememberCoroutineScope()
    WeBottomNavigationBar {
        WeBottomNavigationBarItem(
            title = stringResource(id = R.string.string_ai_chat_screen_navigation_contact),
            selected = pagerState.currentPage == AiScreenSelectEnum.CONTACTS.ordinal,
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(AiScreenSelectEnum.CONTACTS.ordinal)
                }
            },
            unSelectImageVector = WeIcons.ContactsUnselect,
            selectImageVector = WeIcons.ContactsSelect
        )
        WeBottomNavigationBarItem(
            title = stringResource(id = R.string.string_ai_chat_screen_navigation_me),
            selected = pagerState.currentPage == AiScreenSelectEnum.ME.ordinal,
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(AiScreenSelectEnum.ME.ordinal)
                }
            },
            unSelectImageVector = WeIcons.MeUnselect,
            selectImageVector = WeIcons.MeSelect
        )
    }
}


@PreviewLightDark
@Composable
fun PreviewAiChatScreenUi(){
    QuicklyTheme {
        AiChatScreenUi(
            contactsContent = {},
            meContent = {}
        )
    }
}