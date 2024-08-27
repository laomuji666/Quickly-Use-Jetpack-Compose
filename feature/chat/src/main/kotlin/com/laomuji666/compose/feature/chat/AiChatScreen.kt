package com.laomuji666.compose.feature.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.icons.TopBarAdd
import com.laomuji666.compose.core.ui.we.icons.TopBarSearch
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeNavigationBarItem
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTopBar
import com.laomuji666.compose.core.ui.we.widget.WeTopBarAction
import com.laomuji666.compose.core.ui.we.widget.WeTopBarActionSpace
import com.laomuji666.compose.feature.chat.icons.Contact
import com.laomuji666.compose.feature.chat.icons.Me
import com.laomuji666.compose.feature.chat.icons.Message
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
    val coroutineScope = rememberCoroutineScope()
    WeScaffold(
        topBar = {
            if(pagerState.currentPage != AiScreenSelectEnum.ME.ordinal){
                TopBar()
            }
        },
        bottomBar = {
            WeNavigationBar {
                WeNavigationBarItem(
                    title = "消息",
                    selected = pagerState.currentPage == AiScreenSelectEnum.MESSAGE.ordinal,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(AiScreenSelectEnum.MESSAGE.ordinal)
                        }
                    },
                    imageVector = WeIcons.Message
                )
                WeNavigationBarItem(
                    title = "联系人",
                    selected = pagerState.currentPage == AiScreenSelectEnum.CONTACT.ordinal,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(AiScreenSelectEnum.CONTACT.ordinal)
                        }
                    },
                    imageVector = WeIcons.Contact
                )
                WeNavigationBarItem(
                    title = "我",
                    selected = pagerState.currentPage == AiScreenSelectEnum.ME.ordinal,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(AiScreenSelectEnum.ME.ordinal)
                        }
                    },
                    imageVector = WeIcons.Me
                )
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) {

        }
    }
}

@Composable
private fun TopBar(){
    WeTopBar(
        title = "Ai聊天",
        actions = {
            WeTopBarAction(
                imageVector = WeIcons.TopBarSearch
            )
            WeTopBarActionSpace()
            WeTopBarAction(
                imageVector = WeIcons.TopBarAdd
            )
        }
    )
}


@Preview
@Composable
fun PreviewAiChatScreenUi(){
    QuicklyTheme {
        AiChatScreenUi()
    }
}