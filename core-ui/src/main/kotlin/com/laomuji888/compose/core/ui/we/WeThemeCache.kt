package com.laomuji888.compose.core.ui.we

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import com.laomuji888.compose.core.ui.we.colorscheme.WeThemeColorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


object WeThemeCache {
    private fun Context.getWeThemeCache() =
        getSharedPreferences("${packageName}_WeThemeCache", Context.MODE_PRIVATE)

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun initWeThemeCache(application: Application) {
        looperWeThemeColorType(application)
    }

    private fun looperWeThemeColorType(context: Context) {
        //缓存设为正在使用的值
        WeThemeColorType.setWeThemeColorType(
            context.getWeThemeCache().getString("WeThemeColorType", null)
        )
        //值变动时进行缓存
        coroutineScope.launch {
            WeThemeColorType.currentWeThemeColorType.collect {
                context.getWeThemeCache().edit {
                    putString("WeThemeColorType", it.javaClass.name)
                }
            }
        }
    }
}

