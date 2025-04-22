package com.laomuji666.compose.core.logic

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LanguageModule {
    @Binds
    @Singleton
    abstract fun bindLanguage(defaultLocator: DefaultLanguage): Language
}