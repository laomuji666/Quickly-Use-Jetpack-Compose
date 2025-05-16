package com.laomuji888.compose.core.logic.repository.module.chat

import com.laomuji888.compose.core.logic.database.entity.MessageInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface ChatRepository {
    fun sendMessage(
        account:Long,
        text:String,
        nickname:String
    )

    fun getMessageList(account: Long): Flow<List<MessageInfoEntity>>
}