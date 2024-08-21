package com.laomuji666.compose.core.logic.cache

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {
    @Singleton
    @Provides
    fun bindCacheUtil(
        @ApplicationContext context: Context
    ):CacheUtil{
        return CacheUtil(CacheAndroid(context))
    }
}