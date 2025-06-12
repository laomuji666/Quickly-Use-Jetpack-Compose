package com.laomuji1999.compose.launcher

import android.app.Activity
import android.app.PendingIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun selectMobileLauncher(onSuccess:(String)->Unit, onFail:()->Unit): () -> Unit {
    val activity = LocalView.current.context as Activity
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->
        try {
            val phoneNumber = Identity
                .getSignInClient(activity)
                .getPhoneNumberFromIntent(result.data)
            onSuccess(phoneNumber)
        } catch (_: Exception) {
            onFail()
        }
    }
    val getPhoneNumberHint = {
        try {
            val request: GetPhoneNumberHintIntentRequest = GetPhoneNumberHintIntentRequest.builder().build()
            Identity.getSignInClient(activity)
                .getPhoneNumberHintIntent(request)
                .addOnSuccessListener { result: PendingIntent ->
                    try {
                        launcher.launch(IntentSenderRequest.Builder(result).build())
                    } catch (_: Exception) {
                        onFail()
                    }
                }
                .addOnFailureListener {
                    onFail()
                }
        } catch (_: Exception) {
            onFail()
        }
        Unit
    }
    return getPhoneNumberHint
}