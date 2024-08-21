package com.laomuji666.compose.core.logic.http

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpModule {
    @Singleton
    @Provides
    fun bindHttpRepository(
        httpService: HttpService
    ):HttpRepository{
        return HttpRepository(httpService.client)
    }
}