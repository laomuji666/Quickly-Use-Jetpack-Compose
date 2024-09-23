package com.laomuji666.compose.core.logic.http.di

import com.laomuji666.compose.core.logic.http.HttpRepository
import com.laomuji666.compose.core.logic.http.HttpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun bindHttpRepository(
        httpService: HttpService
    ): HttpRepository {
        return HttpRepository(httpService.client)
    }
}