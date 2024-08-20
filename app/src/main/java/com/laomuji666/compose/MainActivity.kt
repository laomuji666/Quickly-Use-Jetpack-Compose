package com.laomuji666.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.laomuji666.compose.core.ui.QuicklyTheme
import com.laomuji666.compose.feature.hello.HelloScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        checkKeepOnScreenCondition(splashScreen = splashScreen)

        enableEdgeToEdge()
        setContent {
            QuicklyTheme {
                HelloScreen()
            }
        }
    }

    /**
     * 检查是否需要保留启动屏幕
     * 默认在setContent后就不再显示启动屏幕
     * 如果需要在启动时初始化一些三方SDK,可以保留启动屏幕到初始化完成后再显示
     */
    private fun checkKeepOnScreenCondition(splashScreen: SplashScreen){
        var uiState: MainUiState by mutableStateOf(MainUiState.Loading)
        splashScreen.setKeepOnScreenCondition {
            uiState == MainUiState.Loading
        }
        if(uiState == MainUiState.Loading){
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState
                        .collect{
                            uiState = it
                        }
                }
            }
        }
    }
}