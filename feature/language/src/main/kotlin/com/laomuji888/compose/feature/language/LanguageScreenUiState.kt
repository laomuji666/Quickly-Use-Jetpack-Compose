package com.laomuji888.compose.feature.language

import com.laomuji888.compose.core.logic.AppLanguages

data class LanguageScreenUiState(
    val usingLanguage: AppLanguages = AppLanguages.FlowSystem,
    val appLanguageList: List<AppLanguages> = AppLanguages.getAppLanguageList(),
)