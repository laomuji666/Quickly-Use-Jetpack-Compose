package com.laomuji1999.compose.core.logic.http.di

import android.content.Context
import com.laomuji1999.compose.core.logic.common.dispatchers.IoCoroutineScope
import com.laomuji1999.compose.core.logic.http.ConnectivityObserver
import com.laomuji1999.compose.core.logic.http.HttpRepository
import com.laomuji1999.compose.core.logic.http.HttpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpModule {
    @Singleton
    @Provides
    fun bindHttpRepository(
        httpService: HttpService
    ): HttpRepository {
        return HttpRepository(httpService.client)
    }

    @Singleton
    @Provides
    fun bindConnectivityObserver(
        @ApplicationContext context: Context,
        @IoCoroutineScope coroutineScope: CoroutineScope
    ): ConnectivityObserver {
        return ConnectivityObserver(context = context, coroutineScope = coroutineScope)
    }
}