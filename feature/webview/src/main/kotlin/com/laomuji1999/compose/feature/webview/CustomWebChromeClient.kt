package com.laomuji1999.compose.feature.webview

import android.os.Message
import android.webkit.ConsoleMessage
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import com.laomuji1999.compose.core.logic.common.Log

class CustomWebChromeClient(
    private val onProgressChanged: (progress: Int) -> Unit,
    private val onOpenNewWindow: (url: String) -> Unit,
    private val onReceivedTitle: (title: String?) -> Unit
) : WebChromeClient() {
    companion object{
        private const val TAG = "tag_web_view_web_chrome_client"
    }

    //打印控制台日志
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        Log.debug(TAG, consoleMessage.message())
        return super.onConsoleMessage(consoleMessage)
    }

    //网页加载进度
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        onProgressChanged(newProgress)
    }

    //拦截alert,显示自定义的弹窗
    override fun onJsAlert(
        webView: WebView,
        url: String?,
        message: String?,
        jsResult: JsResult
    ): Boolean {
        val builder: AlertDialog.Builder = AlertDialog.Builder(webView.context)
        builder.setMessage(message).setPositiveButton("OK") { arg0, _ -> arg0.dismiss() }.show()
        jsResult.confirm()
        return true
    }

    //拦截打开新窗口
    override fun onCreateWindow(
        view: WebView,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message
    ): Boolean {
        val newWebView = WebView(view.context)
        newWebView.webViewClient = object : WebViewClient() {
            private var isOpened = false
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                if(isOpened){
                    return
                }
                isOpened = true
                onOpenNewWindow(url)
            }
        }
        val transport = resultMsg.obj as? WebView.WebViewTransport
        transport?.let {
            it.webView = newWebView
            resultMsg.sendToTarget()
        }
        return true
    }

    //标题变化
    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        onReceivedTitle(title)
    }
}