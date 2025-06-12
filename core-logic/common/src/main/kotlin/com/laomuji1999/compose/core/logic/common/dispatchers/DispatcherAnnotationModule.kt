package com.laomuji1999.compose.core.logic.common.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object DispatcherAnnotationModule {
    @Provides
    @DispatcherAnnotation(DispatcherAnnotations.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @IoCoroutineScope
    fun providesIoCoroutineScope(
        @DispatcherAnnotation(DispatcherAnnotations.IO) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)


    @Provides
    @DispatcherAnnotation(DispatcherAnnotations.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @DefaultCoroutineScope
    fun providesDefaultCoroutineScope(
        @DispatcherAnnotation(DispatcherAnnotations.Default) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)


    @Provides
    @DispatcherAnnotation(DispatcherAnnotations.Main)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @MainCoroutineScope
    fun providesMainCoroutineScope(
        @DispatcherAnnotation(DispatcherAnnotations.Main) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}