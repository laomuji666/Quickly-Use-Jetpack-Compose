package com.laomuji666.compose.feature.webview

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient(
    private val openBrowser: (url: String) -> Unit,
    private val onUrlChanged: (url: String) -> Unit
) : WebViewClient(){
    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        if("about:blank" == url){
            return
        }
        onUrlChanged(url)
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val url = request.url.toString()
        openBrowser(url)
        return false
    }
}