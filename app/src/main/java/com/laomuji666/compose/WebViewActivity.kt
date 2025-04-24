package com.laomuji666.compose

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.laomuji666.compose.core.ui.screen.SlideActivity
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.feature.webview.WebViewScreenRoute
import com.laomuji666.compose.feature.webview.WebViewScreenRoute.Companion.composeWebViewScreen
import com.laomuji666.compose.feature.webview.WebViewScreenRoute.Companion.navigateToWebViewScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewActivity : SlideActivity() {
    companion object {
        fun openWebViewActivity(url: String, context: Context) {
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
                ) {
                    composeWebViewScreen(
                        onBackClick = {
                            finish()
                        },
                        onOpenNewWindow = {
                            navController.navigateToWebViewScreen(it)
                        }
                    )
                }
                if (isNeedLoadUrl) {
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

    override fun finish() {
        super.finish()
        val activityManager: ActivityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (appTask in activityManager.appTasks) {
            val isAllFinish = appTask.taskInfo.numActivities == 0
            val isSameName =
                componentName.shortClassName == appTask.taskInfo.baseIntent.component?.shortClassName
            if (isAllFinish && isSameName) {
                appTask.finishAndRemoveTask()
            }
        }
    }

}