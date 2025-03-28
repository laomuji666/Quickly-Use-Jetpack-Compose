package com.laomuji666.compose.feature.webview

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Device
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBarAction

@Composable
fun WebViewScreen(
    viewModel: WebViewScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onOpenNewWindow: (String)->Unit,
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WebViewScreenUi(
        uiState = uiState,
        onAction = viewModel::onAction,
        onBackClick = onBackClick,
        onOpenNewWindow = onOpenNewWindow
    )
}

@Composable
private fun WebViewScreenUi(
    uiState: WebViewScreenUiState,
    onAction: (WebViewScreenAction)->Unit,
    onBackClick: ()->Unit,
    onOpenNewWindow: (String)->Unit,
){
    val context = LocalContext.current
    var webView:WebView? by remember { mutableStateOf(null) }
    WeScaffold(
        topBar = {
            WeTopNavigationBar(
                title = uiState.title,
                onBackClick = {
                    if(webView != null && webView!!.canGoBack()){
                        webView!!.goBack()
                    }else{
                        onBackClick()
                    }
                },
                actions = {
                    WeTopNavigationBarAction(
                        imageVector = WeIcons.Device,
                        onActionClick = {
                            WebViewScreenUtil.openBrowser(url = uiState.url, context = context)
                        }
                    )
                }
            )
        }
    ) {
        WebViewScreenProgressView(progress = uiState.progress)
        webView = composeWebView(
            url = uiState.url,
            onProgressChanged = {
                onAction(WebViewScreenAction.OnProgressChanged(it))
            },
            onOpenNewWindow = onOpenNewWindow,
            onReceivedTitle = {
                onAction(WebViewScreenAction.OnReceivedTitle(it))
            },
            onUrlChanged = {
                onAction(WebViewScreenAction.OnUrlChanged(it))
            }
        )
    }

}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun composeWebView(
    url: String,
    onProgressChanged: (Int)->Unit,
    onOpenNewWindow: (String)->Unit,
    onReceivedTitle: (String?)->Unit,
    onUrlChanged:(String)->Unit
):WebView?{
    if(LocalInspectionMode.current){
        return null
    }
    var webView:WebView? by remember { mutableStateOf(null) }
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            webView = WebView(context).apply {
                //开启JavaScript
                settings.javaScriptEnabled = true
                //开启dom
                settings.domStorageEnabled = true
                //允许缩放
                settings.setSupportZoom(true)
                //支持ViewPort元标记
                settings.useWideViewPort = true
                //首次加载自动缩放到完整显示内容的比例
                settings.loadWithOverviewMode = true
                //混合加载网络协议
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                //支持多窗口模式
                settings.setSupportMultipleWindows(true)
                //设置没有用户的手势触摸也可以播放视频
                settings.mediaPlaybackRequiresUserGesture = false

                //设置WebViewClient,正常链接直接在当前WebView中打开,
                webViewClient = CustomWebViewClient(
                    openBrowser = {
                        if (url.startsWith("http") || url.startsWith("https")) {
                            loadUrl(url)
                        } else {
                            WebViewScreenUtil.openBrowser(url = url, context = context)
                        }
                    },
                    onUrlChanged = onUrlChanged
                )
                //设置WebChromeClient
                webChromeClient = CustomWebChromeClient(
                    onProgressChanged = onProgressChanged,
                    onOpenNewWindow = { url->
                        if (url.startsWith("http") || url.startsWith("https")) {
                            onOpenNewWindow(url)
                        } else {
                            WebViewScreenUtil.openBrowser(url = url, context = context)
                        }
                    },
                    onReceivedTitle = onReceivedTitle,
                )

                //加载url
                if(url.isNotEmpty()){
                    loadUrl(url)
                }
            }
            webView!!
        },
        update = {
            if(url != it.url){
                it.loadUrl(url)
            }
        }
    )
    return webView
}

@Composable
private fun WebViewScreenProgressView(
    progress: Int
){
    if(progress < 100){
        Spacer(modifier = Modifier.height(1.dp).fillMaxWidth(progress / 100f).background(WeTheme.colorScheme.cursorColor))
    }
}

@Preview
@Composable
private fun PreviewWebViewScreenUi() {
    QuicklyTheme {
        WebViewScreenUi(
            uiState = WebViewScreenUiState(
                url = "https://www.google.com",
                title = "GoogleGoogleGoogleGoogleGoogleGoogleGoogleGoogleGoogle",
                progress = 75
            ),
            onAction = {

            },
            onBackClick = {

            },
            onOpenNewWindow = {

            }
        )
    }
}