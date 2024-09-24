package com.laomuji666.compose.core.logic.database.di

import android.app.Application
import androidx.room.Room
import com.laomuji666.compose.core.logic.database.Database
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
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideContactDao(database: Database) = database.contactDao()
}