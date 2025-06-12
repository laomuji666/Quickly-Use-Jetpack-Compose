package com.laomuji1999.compose.core.logic.common

/**
 * 限制日志只能在debug模式下输出
 */
object Log{
    fun debug(tag: String, msg: String?){
        if(!BuildConfig.DEBUG){
            return
        }
        android.util.Log.d(tag, "$msg")
    }
}
