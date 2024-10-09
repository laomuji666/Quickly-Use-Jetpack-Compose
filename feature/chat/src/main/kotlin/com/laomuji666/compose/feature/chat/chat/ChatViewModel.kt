package com.laomuji666.compose.feature.chat.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.repository.module.chat.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val chatRepository: ChatRepository
):ViewModel() {
    private val account: Long = (savedStateHandle.get<String>(ACCOUNT)!!).toLong()
    private val nickname: String = savedStateHandle[NICKNAME]!!

    private val _messageList = chatRepository.getMessageList(account)

    val uiState = _messageList.map {
        ChatScreenUiState(
            nickname = nickname,
            messageList = it
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), ChatScreenUiState()
    )

    fun sendMessage(text: String) {
        chatRepository.sendMessage(account, text, nickname)
    }
}