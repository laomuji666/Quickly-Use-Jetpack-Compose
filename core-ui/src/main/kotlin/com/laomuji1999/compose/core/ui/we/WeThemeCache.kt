package com.laomuji1999.compose.core.ui.we

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import com.laomuji1999.compose.core.ui.we.colorscheme.WeThemeColorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * 持久化缓存设计系统的配置
 * 设计系统中出现持久化缓存,感觉挺奇怪的.
 * @author laomuji666
 * @since 2025/5/23
 */
object WeThemeCache {
    private fun Context.getWeThemeCache() =
        getSharedPreferences("${packageName}_WeThemeCache", Context.MODE_PRIVATE)

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /**
     * 初始化设计系统缓存
     * @param application Application
     */
    fun initWeThemeCache(application: Application) {
        looperWeThemeColorType(application)
    }

    /**
     * 监听设计系统颜色类型变化,在发生变化时立即更新缓存.
     * @param context Context
     */
    private fun looperWeThemeColorType(context: Context) {
        //使用缓存的值作为正在使用的颜色类型
        WeThemeColorType.setWeThemeColorType(
            context.getWeThemeCache().getString("WeThemeColorType", null)
        )
        //在协程中持续监听
        coroutineScope.launch {
            WeThemeColorType.currentWeThemeColorType.collect {
                context.getWeThemeCache().edit {
                    putString("WeThemeColorType", it.javaClass.name)
                }
            }
        }
    }
}

