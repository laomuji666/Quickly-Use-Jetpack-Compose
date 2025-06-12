package com.laomuji1999.compose.core.logic.analytics.di

import com.laomuji1999.compose.core.logic.analytics.Analytics
import com.laomuji1999.compose.core.logic.analytics.impl.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalytics(): Analytics {
        return FirebaseAnalytics()
    }
}