package com.laomuji666.compose.feature.youtubedl.model

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class YoutubeDLModule {
    @Binds
    @Singleton
    abstract fun bindYoutubeDLService(youtubeDLServiceImpl: YoutubeDLServiceImpl): YoutubeDLService
}