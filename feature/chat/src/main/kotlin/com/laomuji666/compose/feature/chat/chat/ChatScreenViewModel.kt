package com.laomuji666.compose.feature.chat.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.laomuji666.compose.core.logic.database.dao.ContactDao
import com.laomuji666.compose.core.logic.notification.NotificationHelper
import com.laomuji666.compose.core.logic.repository.module.chat.ChatRepository
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    contactDao: ContactDao,
    private val chatRepository: ChatRepository,
    private val notificationHelper: NotificationHelper
):ViewModel() {
    //获取联系人的信息
    private val contactInfo = contactDao.getByAccount((savedStateHandle.toRoute<RouteChatScreen>()).account)
    //获取聊天列表,flow,实际上返回的是数据库的,在数据库更新时自动emit,所以一直都是最新的值.
    private val _messageList = chatRepository.getMessageList(contactInfo.account)

    private val _inputText = MutableStateFlow("")

    val uiState = combine(_messageList, _inputText) { messageList, inputText ->
        ChatScreenUiState(
            receiveAvatar = contactInfo.avatar,
            nickname = contactInfo.nickname,
            messageList = messageList,
            inputText = inputText
        )
    }.stateInTimeout(viewModelScope, ChatScreenUiState())

    fun onAction(action: ChatScreenAction) {
        when(action) {
            is ChatScreenAction.SetInputText -> setInputText(action.text)
            ChatScreenAction.SendInputText -> sendInputText()
            ChatScreenAction.DismissNotification -> dismissNotification()
        }
    }

    private fun setInputText(text: String) {
        _inputText.value = text
    }

    private fun sendInputText() {
        if(_inputText.value.isEmpty()) {
            return
        }
        chatRepository.sendMessage(contactInfo.account, _inputText.value, contactInfo.nickname)
        _inputText.value = ""
    }

    private fun dismissNotification(){
        notificationHelper.dismissNotification(contactInfo)
    }
}