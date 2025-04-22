package com.laomuji666.compose.feature.language

import com.laomuji666.compose.core.logic.AppLanguages

sealed interface LanguageScreenAction {
    data class OnLanguageClick(val appLanguage: AppLanguages) : LanguageScreenAction
}