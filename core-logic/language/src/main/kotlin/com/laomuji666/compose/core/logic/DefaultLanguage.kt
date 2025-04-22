package com.laomuji666.compose.core.logic

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.laomuji666.compose.core.logic.common.cache.CacheUtil
import java.util.Locale
import javax.inject.Inject

class DefaultLanguage @Inject constructor(
    cacheUtil: CacheUtil
) : Language {
    companion object {
        private const val DEFAULT_LANGUAGE_USING_LANGUAGE_TAG =
            "DEFAULT_LANGUAGE_USING_LANGUAGE_TAG"
    }

    private var usingLanguageTag by cacheUtil.cacheable(
        DEFAULT_LANGUAGE_USING_LANGUAGE_TAG,
        AppLanguages.FlowSystem.getTag()
    )

    override fun getAppUsingLanguage(): AppLanguages {
        return AppLanguages.fromTag(usingLanguageTag)
    }

    override fun setAppUsingLanguage(appLanguage: AppLanguages) {
        usingLanguageTag = appLanguage.getTag()
        val locale = if (appLanguage == AppLanguages.FlowSystem) {
            Locale.getDefault()
        } else {
            appLanguage.locale
        }
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(locale))
    }
}