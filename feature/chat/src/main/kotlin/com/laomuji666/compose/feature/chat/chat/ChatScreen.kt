package com.laomuji666.compose.feature.chat.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.More
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutline
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBarAction

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    onBackClick: ()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ChatScreenUi(
        uiState = uiState,
        onBackClick = onBackClick
    )
}

@Composable
private fun ChatScreenUi(
    uiState: ChatScreenUiState,
    onBackClick: ()->Unit
){
    WeScaffold(
        topBar = {
            WeTopNavigationBar(
                title = uiState.nickname,
                onBackClick = onBackClick,
                actions = {
                    WeTopNavigationBarAction(
                        imageVector = WeIcons.More
                    )
                }
            )
            WeTableRowOutline()
        },
        bottomBar = {
            WeTableRowOutline()
            Row(modifier = Modifier
                .background(WeTheme.colorScheme.chatInputBackground)
                .fillMaxWidth()
                .height(WeTheme.dimens.chatInputHeight)
                .padding(horizontal = WeTheme.dimens.navigationBarPaddingHorizontal)
            ) {

            }
        }
    ) {

    }
}

@Preview
@Composable
fun PreviewChatScreenUi(){
    QuicklyTheme {
        ChatScreenUi(
            uiState = ChatScreenUiState(
                nickname = "LaoMuJi"
            ),
            onBackClick = {}
        )
    }
}