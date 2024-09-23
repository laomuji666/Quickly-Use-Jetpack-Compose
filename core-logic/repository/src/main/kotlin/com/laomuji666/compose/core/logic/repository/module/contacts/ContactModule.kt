package com.laomuji666.compose.core.logic.repository.module.contacts

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContactModule {
    @Singleton
    @Provides
    fun bindContactsRepository(): ContactsRepository {
        return ContactsRepository()
    }
}