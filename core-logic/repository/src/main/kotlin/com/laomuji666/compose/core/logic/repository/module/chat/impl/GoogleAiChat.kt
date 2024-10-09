package com.laomuji666.compose.core.logic.repository.module.chat.impl

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.laomuji666.compose.core.logic.common.BuildConfig
import com.laomuji666.compose.core.logic.database.dao.MessageDao
import com.laomuji666.compose.core.logic.database.entity.MessageInfoEntity
import com.laomuji666.compose.core.logic.repository.module.chat.ChatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Singleton

@Singleton
internal class GoogleAiChat(
    private val messageDao: MessageDao
) : ChatRepository {

    private val coroutineScope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())

    private suspend fun getHistory(account: Long) = messageDao.getMessageList(account = account).first().map { message ->
        content(role = if(message.isSend){ "user" }else{ "model" }) {
            text(message.text)
        }
    }

    override fun sendMessage(
        account: Long,
        text: String,
        nickname: String
    ) {
        messageDao.insert(
            MessageInfoEntity(
                account = account,
                text = text,
                isSend = true
            )
        )
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = BuildConfig.API_KEY,
            systemInstruction = content {
                content {
                    text("请像友好的${nickname}一样回复此聊天对话")
                }
            }
        )
        coroutineScope.launch {
            try {
                generativeModel.startChat(history = getHistory(account = account)).sendMessage(text).text
            }catch (_:Exception){
                null
            }?.let {
                messageDao.insert(
                    MessageInfoEntity(
                        account = account,
                        text = it,
                        isSend = false
                    )
                )
            }
        }
    }

    override fun getMessageList(account: Long): Flow<List<MessageInfoEntity>> = messageDao.getMessageList(account)
}