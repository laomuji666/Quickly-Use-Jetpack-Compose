package com.laomuji1999.compose.core.logic.authenticate

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.laomuji1999.compose.core.logic.common.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 配置谷歌登录:
 * https://developer.android.com/identity/sign-in/credential-manager-siwg
 */
class GoogleAuthenticate {
    companion object{
        private const val WEB_CLIENT_ID = BuildConfig.WEB_CLIENT_ID
    }

    fun requestLogin(activityContext:Context, onSuccess:(email:String,idToken:String)->Unit, onFail:()->Unit){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request: GetCredentialRequest = GetCredentialRequest.Builder()
                    .addCredentialOption(GetSignInWithGoogleOption.Builder(WEB_CLIENT_ID).build())
                    .build()
                val credentialManager = CredentialManager.create(activityContext)
                val result = credentialManager.getCredential(
                    request = request,
                    context = activityContext,
                )
                handleSignIn(
                    result = result,
                    onSuccess = onSuccess,
                    onFail = onFail
                )
            } catch (_: Exception) {
                onFail()
            }
        }
    }
    private fun handleSignIn(result: GetCredentialResponse, onSuccess:(String,String)->Unit, onFail:()->Unit) {
        when(val credential = result.credential){
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        onSuccess(googleIdTokenCredential.id, googleIdTokenCredential.idToken)
                    } catch (_: Exception) {
                        onFail()
                    }
                }else{
                    onFail()
                }
            }
            else -> {
                onFail()
            }
        }
    }
}