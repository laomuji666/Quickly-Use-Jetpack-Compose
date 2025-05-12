package com.laomuji888.compose.core.logic.repository.module.contacts

import com.laomuji888.compose.core.logic.database.dao.ContactDao
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
    fun bindContactsRepository(
        contactDao: ContactDao
    ): ContactRepository {
        return ContactRepository(contactDao)
    }
}