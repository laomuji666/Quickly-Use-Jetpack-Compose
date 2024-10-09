package com.laomuji666.compose.core.logic.repository.module.chat.di

import com.laomuji666.compose.core.logic.database.dao.MessageDao
import com.laomuji666.compose.core.logic.repository.module.chat.ChatRepository
import com.laomuji666.compose.core.logic.repository.module.chat.impl.GoogleAiChat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ChatModule {
    @Provides
    @Singleton
    fun provideChat(
        messageDao: MessageDao
    ): ChatRepository {
        return GoogleAiChat(messageDao)
    }
}