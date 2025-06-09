package com.laomuji888.compose.core.logic

import android.content.Context
import android.content.res.Resources
import com.laomuji888.compose.res.R
import java.util.Locale

sealed class AppLanguages(open val locale: Locale) {
    data object FlowSystem : AppLanguages(getSystemLanguage()) {
        override val locale: Locale
            get() = getSystemLanguage()
    }
    data object ChineseSimpled : AppLanguages(Locale.forLanguageTag("zh-Hans"))
    data object ChineseTraditional : AppLanguages(Locale.forLanguageTag("zh-Hant"))
    data object English : AppLanguages(Locale("en"))
    data object Spanish : AppLanguages(Locale("es"))
    data object Arabic : AppLanguages(Locale("ar"))
    data object Bengali : AppLanguages(Locale("bn"))

    companion object {
        fun getSystemLanguage(): Locale {
            val locales = Resources.getSystem().configuration.locales
            return if (locales.size() > 0) {
                locales[0]
            } else {
                Locale.getDefault()
            }
        }

        fun fromTag(tag: String?): AppLanguages {
            return when (tag) {
                "zh-Hans" -> ChineseSimpled
                "zh-Hant" -> ChineseTraditional
                "en" -> English
                "es" -> Spanish
                "ar" -> Arabic
                "bn" -> Bengali
                else -> FlowSystem
            }
        }

        fun getAppLanguageList(): List<AppLanguages> {
            return listOf(
                FlowSystem,
                ChineseSimpled,
                ChineseTraditional,
                English,
                Spanish,
                Arabic,
                Bengali
            )
        }
    }

    fun getTag(): String {
        return when (this) {
            is ChineseSimpled -> "zh-Hans"
            is ChineseTraditional -> "zh-Hant"
            is English -> "en"
            is Spanish -> "es"
            is Arabic -> "ar"
            is Bengali -> "bn"
            else -> ""
        }
    }

    fun getDisplayName(context: Context): String {
        if (this is FlowSystem) {
            return context.getString(R.string.string_language_system)
        }
        return locale.displayName
    }
}