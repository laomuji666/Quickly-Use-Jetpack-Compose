package com.laomuji666.compose.feature.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.AppLanguages
import com.laomuji666.compose.core.logic.Language
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class LanguageScreenViewModel @Inject constructor(
    private val language: Language
) : ViewModel() {
    private val _usingLanguage = MutableStateFlow(language.getAppUsingLanguage())
    private val _appLanguageList = MutableStateFlow(AppLanguages.getAppLanguageList())

    val uiState = combine(
        _usingLanguage,
        _appLanguageList,
    ) { usingLanguage, appLanguageList ->
        LanguageScreenUiState(
            usingLanguage = usingLanguage,
            appLanguageList = appLanguageList,
        )
    }.stateInTimeout(viewModelScope, LanguageScreenUiState())

    fun onAction(
        action: LanguageScreenAction,
    ) {
        when (action) {
            is LanguageScreenAction.OnLanguageClick -> {
                _usingLanguage.value = action.appLanguage
                language.setAppUsingLanguage(action.appLanguage)
            }
        }
    }
}