package com.laomuji666.compose.core.logic.common.time

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object TimeUtil {
    private lateinit var timeZone: TimeZone

    fun initTimeUtil(id:String){
        timeZone = TimeZone.getTimeZone(id)
    }

    private fun getTimeFromTimeZone(format: String?, time: Long): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        simpleDateFormat.timeZone = timeZone
        return simpleDateFormat.format(time)
    }

    fun getDateTime(time:Long):String{
        return getTimeFromTimeZone("yyyy-MM-dd HH:mm:ss", time)
    }

    fun getToday():String{
        return getTimeFromTimeZone("yyyy-MM-dd", System.currentTimeMillis())
    }
}