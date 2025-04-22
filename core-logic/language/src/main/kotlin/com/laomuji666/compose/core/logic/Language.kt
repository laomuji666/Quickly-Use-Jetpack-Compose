package com.laomuji666.compose.core.logic

interface Language {
    /**
     * 获取正在使用的语言
     */
    fun getAppUsingLanguage(): AppLanguages

    /**
     * 设置正在使用的语言
     */
    fun setAppUsingLanguage(appLanguage: AppLanguages)
}