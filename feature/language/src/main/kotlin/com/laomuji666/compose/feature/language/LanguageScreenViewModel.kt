package com.laomuji666.compose.feature.language

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.AppLanguages
import com.laomuji666.compose.core.logic.Language
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageScreenViewModel @Inject constructor(
    private val language: Language,
    @ApplicationContext val context: Context
) : ViewModel() {
    private val _usingLanguage = MutableStateFlow(language.getAppUsingLanguage())
    private val _appLanguageList = MutableStateFlow(AppLanguages.getAppLanguageList())
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
                if (systemLocaleSettingIntent != null && action.appLanguage == AppLanguages.FlowSystem) {
                    action.context.startActivity(systemLocaleSettingIntent)
                    viewModelScope.launch {
                        delay(1000)
                        setAppLanguage(action)
                    }
                } else {
                    setAppLanguage(action)
                }
            }
        }
    }

    private fun setAppLanguage(action: LanguageScreenAction.OnLanguageClick) {
        _usingLanguage.value = action.appLanguage
        language.setAppUsingLanguage(action.appLanguage)
    }

    fun updateLanguage() {
        _usingLanguage.value = language.getAppUsingLanguage()
        _appLanguageList.value = AppLanguages.getAppLanguageList()
    }
}