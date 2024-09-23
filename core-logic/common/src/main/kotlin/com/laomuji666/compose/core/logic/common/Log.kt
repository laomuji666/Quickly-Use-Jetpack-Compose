package com.laomuji666.compose.core.logic.common

/**
 * TODO
 * 限制日志只能在debug模式下输出
 */
object Log{
    fun debug(tag: String, msg: String?){
        android.util.Log.d(tag, "$msg")
    }
}
