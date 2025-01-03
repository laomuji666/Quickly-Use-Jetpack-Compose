package com.laomuji666.compose.feature.chat.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.laomuji666.compose.core.logic.database.entity.MessageInfoEntity
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.More
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableInput
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutline
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBarAction

@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel = hiltViewModel(),
    onBackClick: ()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.onAction(ChatScreenAction.DismissNotification)
    }

    ChatScreenUi(
        uiState = uiState,
        onBackClick = onBackClick,
        onInputTextChanged = {
            viewModel.onAction(ChatScreenAction.SetInputText(it))
        },
        onSendInputTextClick = {
            viewModel.onAction(ChatScreenAction.SendInputText)
            softwareKeyboardController?.hide()
        }
    )
}

@Composable
private fun ChatScreenUi(
    uiState: ChatScreenUiState,
    onBackClick: ()->Unit,
    onInputTextChanged: (String)->Unit,
    onSendInputTextClick: ()->Unit
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
            Column(modifier = Modifier.imePadding()) {
                WeTableRowOutline()
                Row(
                    modifier = Modifier
                        .background(WeTheme.colorScheme.chatInputBackground)
                        .fillMaxWidth()
                        .height(WeTheme.dimens.chatInputHeight)
                        .padding(horizontal = WeTheme.dimens.chatPaddingHorizontal),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeTableInput(
                        modifier = Modifier.weight(1f),
                        value = uiState.inputText,
                        onValueChange = onInputTextChanged,
                        onImeNext = onSendInputTextClick
                    )
                    WeButton(text = stringResource(id = com.laomuji666.compose.res.R.string.string_chat_screen_send_text), weButtonType = WeButtonType.WARP, onClick = onSendInputTextClick)
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = WeTheme.dimens.chatPaddingHorizontal),
            reverseLayout = true
        ) {
            itemsIndexed(uiState.messageList){index, item->
                Column(modifier = Modifier.padding(top = WeTheme.dimens.chatPaddingHorizontal)){
                    ChatMessageText(
                        avatar = if(item.isSend) uiState.sendAvatar else uiState.receiveAvatar,
                        text = item.text,
                        isSend = item.isSend
                    )
                    if(index == 0){
                        Spacer(modifier = Modifier.height(WeTheme.dimens.chatPaddingHorizontal))
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatMessageAvatar(
    avatar: String,
    isShow: Boolean = true
){
    Box(modifier = Modifier.size(WeTheme.dimens.chatAvatarSize)){
        if(isShow){
            val imageRequest = ImageRequest
                .Builder(LocalContext.current)
                .data(avatar)
                .diskCacheKey(avatar)
                .build()
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                placeholder = painterResource(id = com.laomuji666.compose.res.R.mipmap.ic_launcher),
                error = painterResource(id = com.laomuji666.compose.res.R.mipmap.ic_launcher),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(WeTheme.dimens.chatAvatarRoundedCornerDp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun ChatMessageText(
    avatar: String,
    text: String,
    isSend: Boolean
){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        ChatMessageAvatar(avatar = avatar, isShow = !isSend)
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = if(isSend)Arrangement.End else Arrangement.Start
        ) {
            SelectionContainer {
                Text(
                    modifier = Modifier
                        .padding(horizontal = WeTheme.dimens.chatPaddingHorizontal)
                        .clip(RoundedCornerShape(WeTheme.dimens.chatAvatarRoundedCornerDp))
                        .background(if (isSend) WeTheme.colorScheme.chatMessageBackgroundSend else WeTheme.colorScheme.chatMessageBackgroundReceive)
                        .padding(WeTheme.dimens.chatPaddingHorizontal),
                    text = text,
                    style = WeTheme.typography.title,
                    color = if (isSend)WeTheme.colorScheme.chatMessageTextSend else WeTheme.colorScheme.chatMessageTextReceive
                )
            }
        }
        ChatMessageAvatar(avatar = avatar, isShow = isSend)
    }
}


@PreviewLightDark
@Composable
fun PreviewChatScreenUi(){
    QuicklyTheme {
        ChatScreenUi(
            uiState = ChatScreenUiState(
                nickname = "LaoMuJi",
                messageList = listOf(
                    MessageInfoEntity(
                        account = 12345,
                        text = "hello hello hello hello hello hello hello hello hello",
                        isSend = false
                    ),
                    MessageInfoEntity(
                        account = 12345,
                        text = "hi",
                        isSend = true
                    )
                )
            ),
            onBackClick = {},
            onInputTextChanged = {},
            onSendInputTextClick = {}
        )
    }
}