package com.laomuji666.compose.feature.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.laomuji666.compose.res.R
import kotlinx.coroutines.launch

@Composable
fun AiChatScreen(){
    AiChatScreenUi()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AiChatScreenUi(){
    val pagerState = rememberPagerState(
        initialPage = AiScreenSelectEnum.MESSAGE.ordinal,
        pageCount = { AiScreenSelectEnum.entries.size }
    )
    var showTopBarActionMenu by rememberSaveable {
        mutableStateOf(false)
    }
    WeScaffold(
        topBar = {
            if(pagerState.currentPage != AiScreenSelectEnum.ME.ordinal){
                TopBar(
                    onMenuClick = {
                        showTopBarActionMenu = true
                    }
                )
            }
        },
        bottomBar = {
            BottomBar(pagerState)
        },
        topBarActionMenu = {
            if(showTopBarActionMenu){

            }

        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}

@Composable
private fun TopBar(
    onMenuClick:()->Unit
){
    WeTopNavigationBar(
        title = stringResource(id = R.string.string_ai_chat_screen_navigation_title),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BottomBar(
    pagerState: PagerState
){
    val coroutineScope = rememberCoroutineScope()
    WeNavigationBar {
        WeNavigationBarItem(
            title = stringResource(id = R.string.string_ai_chat_screen_navigation_message),
            selected = pagerState.currentPage == AiScreenSelectEnum.MESSAGE.ordinal,
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(AiScreenSelectEnum.MESSAGE.ordinal)
                }
            },
            unSelectImageVector = WeIcons.ChatsUnselect,
            selectImageVector = WeIcons.ChatsSelect
        )
        WeNavigationBarItem(
            title = stringResource(id = R.string.string_ai_chat_screen_navigation_contact),
            selected = pagerState.currentPage == AiScreenSelectEnum.CONTACT.ordinal,
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(AiScreenSelectEnum.CONTACT.ordinal)
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