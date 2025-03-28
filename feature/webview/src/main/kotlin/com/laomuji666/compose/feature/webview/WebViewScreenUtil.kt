package com.laomuji666.compose.feature.webview

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

object WebViewScreenUtil {
    fun openBrowser(url: String, context: Context){
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(url.toUri())
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}