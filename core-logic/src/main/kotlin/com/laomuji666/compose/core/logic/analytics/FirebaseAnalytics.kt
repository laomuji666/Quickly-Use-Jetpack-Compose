package com.laomuji666.compose.core.logic.analytics

import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import javax.inject.Singleton

@Singleton
internal class FirebaseAnalytics : Analytics {

    override fun logEvent(name: String, bundle: Bundle?) {
        Firebase.analytics.logEvent(name, bundle)
    }
}