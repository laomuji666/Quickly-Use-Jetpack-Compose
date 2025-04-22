package com.laomuji666.compose.core.logic

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.laomuji666.compose.core.logic.common.cache.CacheUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultLanguage @Inject constructor(
    @ApplicationContext context: Context,
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

    private val systemLocaleSettingIntent =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val intent = Intent(Settings.ACTION_APP_LOCALE_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            if (context.packageManager.queryIntentActivities(
                    intent,
                    PackageManager.MATCH_ALL
                ).isNotEmpty()
            ) {
                intent
            } else {
                null
            }
        } else {
            null
        }

    override fun getAppUsingLanguage(): AppLanguages {
        return AppLanguages.fromTag(usingLanguageTag)
    }

    override fun setAppUsingLanguage(appLanguage: AppLanguages) {
        usingLanguageTag = appLanguage.getTag()
        val needUpdateLanguage = if (systemLocaleSettingIntent == null) {
            true
        } else {
            appLanguage != AppLanguages.FlowSystem
        }

        if (needUpdateLanguage) {
            val locale = if (appLanguage == AppLanguages.FlowSystem) {
                AppLanguages.getSystemLanguage()
            } else {
                appLanguage.locale
            }
            val applicationLocale = AppCompatDelegate.getApplicationLocales().get(0)
            if (applicationLocale == null || applicationLocale.toLanguageTag() != locale.toLanguageTag()) {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(locale))
            }
        }
    }

    override fun getSystemLanguagesHandleIntent(): Intent? {
        return systemLocaleSettingIntent
    }

    override fun initLanguage() {
        val appUsingLanguage = getAppUsingLanguage()
        if (appUsingLanguage != AppLanguages.FlowSystem) {
            return
        }
        setAppUsingLanguage(AppLanguages.FlowSystem)
    }
}