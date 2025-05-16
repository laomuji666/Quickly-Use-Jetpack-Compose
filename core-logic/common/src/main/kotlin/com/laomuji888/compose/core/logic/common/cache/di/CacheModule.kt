package com.laomuji888.compose.core.logic.common.cache.di

import android.content.Context
import com.laomuji888.compose.core.logic.common.cache.CacheUtil
import com.laomuji888.compose.core.logic.common.cache.impl.CacheAndroid
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
    fun provideCacheUtil(
        @ApplicationContext context: Context
    ): CacheUtil {
        return CacheUtil(CacheAndroid(context))
    }
}