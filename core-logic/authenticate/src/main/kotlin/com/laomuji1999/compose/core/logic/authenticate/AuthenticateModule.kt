package com.laomuji1999.compose.core.logic.authenticate

import com.laomuji1999.compose.core.logic.authenticate.biometric.BiometricAuthenticate
import com.laomuji1999.compose.core.logic.authenticate.biometric.DefaultBiometricAuthenticate
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticateModule {

    @Binds
    @Singleton
    fun bindBiometricAuthenticate(biometricAuthenticate: DefaultBiometricAuthenticate): BiometricAuthenticate
}