package com.laomuji666.compose.feature.language

import android.content.Context
import com.laomuji666.compose.core.logic.AppLanguages

sealed interface LanguageScreenAction {
    data class OnLanguageClick(val appLanguage: AppLanguages, val context: Context) : LanguageScreenAction
}