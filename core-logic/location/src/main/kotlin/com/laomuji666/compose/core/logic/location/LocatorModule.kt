package com.laomuji666.compose.core.logic.location

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocatorModule {
    @Binds
    @Singleton
    abstract fun bindLocator(defaultLocator: DefaultLocator): Locator
}