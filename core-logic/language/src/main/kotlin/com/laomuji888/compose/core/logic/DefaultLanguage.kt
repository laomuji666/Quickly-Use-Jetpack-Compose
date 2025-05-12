package com.laomuji888.compose.core.logic

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.laomuji888.compose.core.logic.common.cache.CacheUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultLanguage @Inject constructor(
    @ApplicationContext context: Context, cacheUtil: CacheUtil
) : Language {
    companion object {
        private const val DEFAULT_LANGUAGE_USING_LANGUAGE_TAG =
            "DEFAULT_LANGUAGE_USING_LANGUAGE_TAG"
    }

    private var usingLanguageTag by cacheUtil.cacheable(
        DEFAULT_LANGUAGE_USING_LANGUAGE_TAG, AppLanguages.FlowSystem.getTag()
    )

    private val systemLocaleSettingIntent =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val intent = Intent(Settings.ACTION_APP_LOCALE_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            if (context.packageManager.queryIntentActivities(
                    intent, PackageManager.MATCH_ALL
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

    private val languageInitCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (getAppUsingLanguage() == AppLanguages.FlowSystem) {
                setAppUsingLanguage(AppLanguages.FlowSystem)
            }
        }

        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {}
    }

    override fun initLanguage(application: Application) {
        application.unregisterActivityLifecycleCallbacks(languageInitCallbacks)
        application.registerActivityLifecycleCallbacks(languageInitCallbacks)
    }
}