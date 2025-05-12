package com.laomuji888.compose.feature.webview

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.laomuji888.compose.core.logic.common.Log

class CustomWebViewClient(
    private val openBrowser: (url: String) -> Unit,
    private val onUrlChanged: (url: String) -> Unit
) : WebViewClient(){
    private var currentUrl: String? = null

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        if(url != "about:blank" && url != currentUrl){
            currentUrl = url
            Log.debug("tag_url", currentUrl)
            onUrlChanged(url)
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        currentUrl = null
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val url = request.url.toString()
        openBrowser(url)
        return false
    }
}