package com.laomuji666.compose.core.logic.notification.di

import android.content.Context
import com.laomuji666.compose.core.logic.common.cache.CacheUtil
import com.laomuji666.compose.core.logic.notification.NotificationHelper
import com.laomuji666.compose.core.logic.notification.FirebaseNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {
    @Provides
    @Singleton
    fun provideFirebaseNotification(): FirebaseNotification {
        return FirebaseNotification()
    }

    @Provides
    @Singleton
    fun provideNotificationHelper(
        @ApplicationContext context: Context,
        cacheUtil: CacheUtil
    ): NotificationHelper {
        return NotificationHelper(context, cacheUtil)
    }

}