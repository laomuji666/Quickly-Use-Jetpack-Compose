package com.laomuji666.compose.core.logic.authenticate.biometric

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.fragment.app.FragmentActivity
import com.laomuji666.compose.core.logic.authenticate.biometric.BiometricAuthenticate.BiometricAuthenticateResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class DefaultBiometricAuthenticate @Inject constructor() : BiometricAuthenticate {

    private val _resultFlow:MutableStateFlow<BiometricAuthenticateResult?> = MutableStateFlow(null)

    override val resultFlow: Flow<BiometricAuthenticateResult?> = _resultFlow

    override fun handleBiometric(context: Context, title: String, description: String) {
        val biometricManager = BiometricManager.from(context)
        val authenticators = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //生物认证 或 设备认证(PIN,图案,密码等)
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        }else{
            //生物认证
            BIOMETRIC_STRONG
        }
        when(biometricManager.canAuthenticate(authenticators)){
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                _resultFlow.value = BiometricAuthenticateResult.HardwareUnavailable
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                _resultFlow.value = BiometricAuthenticateResult.HardwareNotFound
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                _resultFlow.value = BiometricAuthenticateResult.NotSetBiometric
                return
            }
            else -> {
                Unit
            }
        }

        val promptInfo = PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setAllowedAuthenticators(authenticators)

        val prompt = BiometricPrompt(
            context as FragmentActivity,
            object : AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    _resultFlow.value = BiometricAuthenticateResult.OtherError(msg = errString.toString())
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    _resultFlow.value = BiometricAuthenticateResult.Success
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    _resultFlow.value = BiometricAuthenticateResult.Fail
                }

            }
        )
        prompt.authenticate(promptInfo.build())
    }

    override fun getBiometricSettingIntent(): Intent {
        val intent: Intent = when{
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                //从API30开始的生物认证
                Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                //从API28开始的生物认证,因为限定了版本,可以抑制DEPRECATION
                @Suppress("DEPRECATION")
                Intent(Settings.ACTION_FINGERPRINT_ENROLL)
            }
            else -> {
                //跳转到安全设置页面
                Intent(Settings.ACTION_SECURITY_SETTINGS)
            }
        }
        return intent
    }
}