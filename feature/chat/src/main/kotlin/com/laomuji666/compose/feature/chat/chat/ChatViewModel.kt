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
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    contactDao: ContactDao,
    private val chatRepository: ChatRepository,
    notificationHelper: NotificationHelper
):ViewModel() {
    private val contactInfo = contactDao.getByAccount((savedStateHandle.toRoute<RouteChatScreen>()).account)

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

    init {
        notificationHelper.dismissNotification(contactInfo)
    }

    fun setInputText(text: String) {
        _inputText.value = text
    }

    fun sendInputText() {
        if(_inputText.value.isEmpty()) {
            return
        }
        chatRepository.sendMessage(contactInfo.account, _inputText.value, contactInfo.nickname)
        _inputText.value = ""
    }
}