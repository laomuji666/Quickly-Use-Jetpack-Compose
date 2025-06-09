package com.laomuji888.compose.feature.language

import android.content.Context
import com.laomuji888.compose.core.logic.AppLanguages

sealed interface LanguageScreenAction {
    data object OnClickBack : LanguageScreenAction
    data class OnLanguageClick(val appLanguage: AppLanguages, val context: Context) : LanguageScreenAction
}