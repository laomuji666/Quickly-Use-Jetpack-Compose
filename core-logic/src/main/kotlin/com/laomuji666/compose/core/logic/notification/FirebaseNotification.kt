package com.laomuji666.compose.core.logic.notification

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class FirebaseNotification : Notification {
    override fun updatePushToken(callback: (String) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                task.exception?.printStackTrace()
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("tag_firebase_push_token", token)
            callback(token)
        })
    }
}