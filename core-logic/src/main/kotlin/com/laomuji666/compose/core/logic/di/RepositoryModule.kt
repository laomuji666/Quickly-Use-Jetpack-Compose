package com.laomuji666.compose.core.logic.di

import android.content.Context
import com.laomuji666.compose.core.logic.repository.contacts.ContactsRepository
import com.laomuji666.compose.core.logic.repository.http.HttpRepository
import com.laomuji666.compose.core.logic.repository.http.HttpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun bindContactsRepository(
        @ApplicationContext context: Context
    ): ContactsRepository {
        return ContactsRepository(context)
    }
}