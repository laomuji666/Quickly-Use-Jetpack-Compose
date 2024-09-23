package com.laomuji666.compose.feature.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.icons.Add
import com.laomuji666.compose.core.ui.we.icons.ChatsSelect
import com.laomuji666.compose.core.ui.we.icons.ChatsUnselect
import com.laomuji666.compose.core.ui.we.icons.ContactsSelect
import com.laomuji666.compose.core.ui.we.icons.ContactsUnselect
import com.laomuji666.compose.core.ui.we.icons.MeSelect
import com.laomuji666.compose.core.ui.we.icons.MeUnselect
import com.laomuji666.compose.core.ui.we.icons.Search
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBarItem
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBarAction
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBarSpace
import com.laomuji666.compose.feature.chat.contacts.ContactsScreen
import com.laomuji666.compose.feature.chat.me.MeScreen
import com.laomuji666.compose.res.R
import kotlinx.coroutines.launch

@Composable
fun AiChatScreen(){
    AiChatScreenUi()
}

@Composable
private fun AiChatScreenUi(){
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
            modifier = Modifier.fillMaxSize()
        ) {
            if(it == AiScreenSelectEnum.MESSAGES.ordinal){
                Text(text = "TODO")
            }

            if(it == AiScreenSelectEnum.CONTACTS.ordinal){
                ContactsScreen()
            }

            if(it == AiScreenSelectEnum.ME.ordinal){
                MeScreen()
            }
        }
    }
}

@Composable
fun AiChatTopBar(
    title:String,
    onMenuClick:()->Unit
){
    WeTopNavigationBar(
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
    WeNavigationBar {
        WeNavigationBarItem(
            title = stringResource(id = R.string.string_ai_chat_screen_navigation_message),
            selected = pagerState.currentPage == AiScreenSelectEnum.MESSAGES.ordinal,
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(AiScreenSelectEnum.MESSAGES.ordinal)
                }
            },
            unSelectImageVector = WeIcons.ChatsUnselect,
            selectImageVector = WeIcons.ChatsSelect
        )
        WeNavigationBarItem(
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
        WeNavigationBarItem(
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
        AiChatScreenUi()
    }
}