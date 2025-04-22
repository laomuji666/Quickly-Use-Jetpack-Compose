package com.laomuji666.compose.core.logic

import android.content.Intent

interface Language {
    /**
     * 获取正在使用的语言
     */
    fun getAppUsingLanguage(): AppLanguages

    /**
     * 设置正在使用的语言
     */
    fun setAppUsingLanguage(appLanguage: AppLanguages)

    /**
     * 获取语言设置的Intent
     */
    fun getSystemLanguagesHandleIntent(): Intent?

    /**
     * 初始化语言设置
     * 在跟随系统语言时,需要调用此方法
     */
    fun initLanguage()
}