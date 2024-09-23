package com.laomuji666.compose.core.logic.notification.impl

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.laomuji666.compose.core.logic.common.Log
import com.laomuji666.compose.core.logic.notification.Notification

class FirebaseNotification : Notification {
    override fun updatePushToken(callback: (String) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                task.exception?.printStackTrace()
                return@OnCompleteListener
            }
            val token = task.result
            Log.debug("tag_firebase_push_token", token)
            callback(token)
        })
    }
}