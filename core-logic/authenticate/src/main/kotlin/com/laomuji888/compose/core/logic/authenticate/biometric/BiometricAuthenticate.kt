package com.laomuji888.compose.core.logic.authenticate.biometric

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.Flow

interface BiometricAuthenticate{
    sealed interface BiometricAuthenticateResult{
        //没有找到硬件
        data object HardwareNotFound : BiometricAuthenticateResult
        //硬件不可用
        data object HardwareUnavailable : BiometricAuthenticateResult
        //用户没有设置生物认证
        data object NotSetBiometric : BiometricAuthenticateResult
        //其它原因导致认证停止,比如用户取消
        data class OtherError(val msg: String) : BiometricAuthenticateResult
        //生物认证成功
        data object Success : BiometricAuthenticateResult
        //生物认证失败
        data object Fail : BiometricAuthenticateResult
    }

    val resultFlow: Flow<BiometricAuthenticateResult?>

    //context 必须是 FragmentActivity
    fun handleBiometric(context: Context, title: String, description: String)

    //获取生物认证设置的意图
    fun getBiometricSettingIntent(): Intent
}