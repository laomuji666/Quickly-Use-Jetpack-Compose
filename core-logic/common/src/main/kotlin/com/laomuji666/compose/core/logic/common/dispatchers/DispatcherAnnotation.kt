package com.laomuji666.compose.core.logic.common.dispatchers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME


@Qualifier
@Retention(RUNTIME)
annotation class DispatcherAnnotation(val dispatcher: DispatcherAnnotations)

enum class DispatcherAnnotations {
    Default,
    IO,
    Main
}

@Qualifier
@Retention(RUNTIME)
annotation class IoCoroutineScope

@Qualifier
@Retention(RUNTIME)
annotation class DefaultCoroutineScope

@Qualifier
@Retention(RUNTIME)
annotation class MainCoroutineScope