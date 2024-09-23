package com.laomuji666.compose.core.logic.notification.di

import com.laomuji666.compose.core.logic.notification.impl.FirebaseNotification
import com.laomuji666.compose.core.logic.notification.Notification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {
    @Provides
    @Singleton
    fun provideNotification(): Notification {
        return FirebaseNotification()
    }
}