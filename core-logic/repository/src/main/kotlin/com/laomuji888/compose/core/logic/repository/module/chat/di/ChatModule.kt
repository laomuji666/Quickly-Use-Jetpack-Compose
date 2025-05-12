package com.laomuji888.compose.core.logic.repository.module.chat.di

import com.laomuji888.compose.core.logic.database.dao.ContactDao
import com.laomuji888.compose.core.logic.database.dao.MessageDao
import com.laomuji888.compose.core.logic.notification.NotificationHelper
import com.laomuji888.compose.core.logic.repository.module.chat.ChatRepository
import com.laomuji888.compose.core.logic.repository.module.chat.impl.GoogleAiChat
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
        contactDao: ContactDao,
        messageDao: MessageDao,
        notificationHelper: NotificationHelper
    ): ChatRepository {
        return GoogleAiChat(
            contactDao = contactDao,
            messageDao = messageDao,
            notificationHelper = notificationHelper
        )
    }
}