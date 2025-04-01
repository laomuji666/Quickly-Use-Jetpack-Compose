package com.laomuji666.compose.feature.webview

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.laomuji666.compose.core.logic.common.Toast

object WebViewScreenUtil {
    fun useOtherAppOpen(url: String, context: Context, onSuccess:()->Unit = {}, onFail:()->Unit = {
        Toast.showText(
            context = context,
            resId = com.laomuji666.compose.res.R.string.string_web_view_screen_open_browser_fail
        )
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("", url))
    }){
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(url.toUri())
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            onSuccess()
        }catch (e:Exception){
            e.printStackTrace()
            onFail()
        }
    }
}