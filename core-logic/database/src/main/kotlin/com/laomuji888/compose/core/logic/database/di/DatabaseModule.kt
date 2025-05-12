package com.laomuji888.compose.core.logic.database.di

import android.app.Application
import androidx.room.Room
import com.laomuji888.compose.core.logic.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): Database {
        return Room
            .databaseBuilder(application, Database::class.java, "quickly_use_jetpack_compose.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideContactDao(database: Database) = database.contactDao()

    @Provides
    @Singleton
    fun provideMessageDao(database: Database) = database.messageDao()

    @Provides
    @Singleton
    fun provideYoutubeDLDao(database: Database) = database.youtubeDLDao()
}