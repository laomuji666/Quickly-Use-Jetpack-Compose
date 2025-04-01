package com.laomuji666.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.feature.webview.WebViewScreenRoute
import com.laomuji666.compose.feature.webview.composeWebViewScreen
import com.laomuji666.compose.feature.webview.navigateToWebViewScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {
    companion object{
        fun openWebViewActivity(url: String, context: Context){
            val intent = Intent(context, WebViewActivity::class.java)
            intent.data = url.toUri()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    private var isNeedLoadUrl = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            QuicklyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = WebViewScreenRoute("")
                ){
                    composeWebViewScreen(
                        onBackClick = {
                            finish()
                        },
                        onOpenNewWindow = {
                            navController.navigateToWebViewScreen(it)
                        }
                    )
                }
                if(isNeedLoadUrl){
                    intent.data?.toString()?.let {
                        navController.navigateToWebViewScreen(it)
                    }
                    isNeedLoadUrl = false
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        openWebViewActivity(intent.data.toString(), this)
    }

}